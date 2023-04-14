package dev.brella.ktornea.spotify

import dev.brella.kornea.errors.common.doOnSuccess
import dev.brella.kornea.errors.common.getOrThrow
import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
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
        SpotifyTrackTestData("Gangnam Style", "03UrZgTINDqvnUMbbIMhql", SpotifyTrack::shouldBeGangnamStyle),
        SpotifyTrackTestData("Call Me Maybe", "20I6sIOMTCkB6w7ryavxtO", SpotifyTrack::shouldBeCallMeMaybe),
        SpotifyTrackTestData("A Long Fall", "7AMA1BVMMitfR8i7fIUv5U", SpotifyTrack::shouldBeALongFall)
    )

    context("Get Track") {
        withData(trackList) { (trackName, trackID, test) ->
            api.getTrack(trackID)
                .testSuccess("Track should exist")
                .doOnSuccess(test)
        }
    }
})