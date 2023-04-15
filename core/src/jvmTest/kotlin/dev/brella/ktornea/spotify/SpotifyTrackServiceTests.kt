package dev.brella.ktornea.spotify

import dev.brella.kornea.errors.common.*
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldBeSameSizeAs
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.test.assertNotNull

@OptIn(ExperimentalKotest::class)
class SpotifyTrackServiceTests : FunSpec({
    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    val client = HttpClient(CIO) {
        install(ContentEncoding) {
            gzip()
            deflate()
            identity()
        }

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 20_000L
            connectTimeoutMillis = 20_000L
            socketTimeoutMillis = 20_000L
        }

        install(HttpCookies)

        install(HttpRequestRetry) {
            retryIf(maxRetries) { _, response ->
                response.status.value.let { it == 401 }
            }
            exponentialDelay()
        }

        install(Auth) {
            bearer {
                refreshTokens {
                    try {
                        client.submitSpotifyClientCredentialsFlow(
                            System.getenv("SPOTIFY_CLIENT_ID"),
                            System.getenv("SPOTIFY_CLIENT_SECRET")
                        ) { markAsRefreshTokenRequest() }
                            .map { (accessToken) -> BearerTokens(accessToken, "") }
                            .getOrThrow()
                    } catch (th: Throwable) {
                        th.printStackTrace()

                        null
                    }
                }

                sendWithoutRequest { request -> request.url.host == "api.spotify.com" }
            }
        }

        expectSuccess = false

        defaultRequest {
            userAgent("Ktornea (+https://github.com/UnderMybrella/ktornea)")
        }
    }
    val api = SpotifyApi(client)
    failfast = true

    val trackList = listOf(
        SpotifyTrackTestData("Gangnam Style", "03UrZgTINDqvnUMbbIMhql", SpotifyTrack::shouldBeGangnamStyle, SpotifyTrackAudioFeatures::shouldBeGangnamStyle),
        SpotifyTrackTestData("Call Me Maybe", "20I6sIOMTCkB6w7ryavxtO", SpotifyTrack::shouldBeCallMeMaybe, SpotifyTrackAudioFeatures::shouldBeCallMeMaybe),
        SpotifyTrackTestData("A Long Fall", "7AMA1BVMMitfR8i7fIUv5U", SpotifyTrack::shouldBeALongFall, SpotifyTrackAudioFeatures::shouldBeALongFall)
    )

    val trackMap = trackList.associateBy(SpotifyTrackTestData::id)

    context("Get Track") {
        withData(trackList) { (_, trackID, testTrack) ->
            api.getTrack(trackID)
                .testSuccess("Track should exist")
                .doOnSuccess(testTrack)
        }
    }

    context("Get Several Tracks") {
        api.getSeveralTracks(trackList.map(SpotifyTrackTestData::id))
            .testSuccess("Tracks should exist")
            .doOnSuccess { spotifyTracks ->
                spotifyTracks shouldBeSameSizeAs trackList

                for (i in spotifyTracks.indices) {
                    val template = trackList[i]
                    test(template.dataTestName()) {
                        val track = spotifyTracks[i]
                        assertNotNull(track) { "Track should exist" }
                        template.testTrack(track)
                    }
                }
            }
    }

    // TODO: Get User's Saved Tracks
    // TODO: Save Tracks for Current User
    // TODO: Remove User's Saved Tracks
    // TODO: Check User's Saved Tracks

    context ("Get Track Audio Features") {
        withData(trackList) { (_, trackID, _, testAudioFeatures) ->
            api.getTrackAudioFeatures(trackID)
                .testSuccess("Audio Features should exist")
                .doOnSuccess(testAudioFeatures)
        }
    }
})