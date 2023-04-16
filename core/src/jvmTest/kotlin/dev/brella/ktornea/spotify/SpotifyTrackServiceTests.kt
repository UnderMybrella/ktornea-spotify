package dev.brella.ktornea.spotify

import dev.brella.kornea.errors.common.*
import dev.brella.ktornea.results.HttpResult
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
import dev.brella.ktornea.spotify.data.enums.EnumSpotifyType
import dev.brella.ktornea.spotify.data.shouldBe
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackRecommendationSeedObject
import dev.brella.ktornea.spotify.data.tracks.analysis.SpotifyTrackAudioAnalysis
import dev.brella.ktornea.spotify.data.tracks.buildSpotifyTrackRecommendationsRequest
import dev.brella.ktornea.spotify.data.types.EnumSpotifySeedType
import dev.brella.ktornea.spotify.services.getRecommendations
import io.kotest.assertions.print.print
import io.kotest.assertions.withClue
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.beEmpty
import io.kotest.matchers.collections.haveSize
import io.kotest.matchers.ints.beGreaterThan
import io.kotest.matchers.shouldBe
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
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@OptIn(ExperimentalKotest::class)
class SpotifyTrackServiceTests : FunSpec({
    val json = Json {
        prettyPrint = true
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

    val trackList = listOf(
        SpotifyTrackTestData(
            "Gangnam Style",
            "03UrZgTINDqvnUMbbIMhql",
            SpotifyTrack::shouldBeGangnamStyle,
            SpotifyTrackAudioFeatures::shouldBeGangnamStyle,
            SpotifyTrackAudioAnalysis::shouldBeGangnamStyle
        ),
        SpotifyTrackTestData(
            "Call Me Maybe",
            "20I6sIOMTCkB6w7ryavxtO",
            SpotifyTrack::shouldBeCallMeMaybe,
            SpotifyTrackAudioFeatures::shouldBeCallMeMaybe,
            SpotifyTrackAudioAnalysis::shouldBeCallMeMaybe
        ),
        SpotifyTrackTestData(
            "A Long Fall",
            "7AMA1BVMMitfR8i7fIUv5U",
            SpotifyTrack::shouldBeALongFall,
            SpotifyTrackAudioFeatures::shouldBeALongFall,
            SpotifyTrackAudioAnalysis::shouldBeALongFall
        )
    )

    context("Get Track") {
        withData(trackList) { (_, trackID, testTrack) ->
            api.getTrack(trackID)
                .testSuccess("Track should exist")
                .doOnSuccess(testTrack)
        }

        test("Edge Cases") {
            api.getTrack("0000000000000000000000")
                .testFailure({ "Non Existing ID should return an error, not ${it.print().value}" }) { failure ->
                    assertIs<HttpResult.ClientError>(failure, "Failure should be HttpResult.ClientError")
                    withClue("Response Status should be 404") { failure.response.status shouldBe HttpStatusCode.NotFound }
                }

            api.getTrack("null")
                .testFailure({ "Malformed ID should return an error, not ${it.print().value}" }) { failure ->
                    assertIs<HttpResult.ClientError>(failure, "Failure should be HttpResult.ClientError")
                    withClue("Response Status should be 400") { failure.response.status shouldBe HttpStatusCode.BadRequest }
                }
        }
    }

    context("Get Several Tracks") {
        api.getSeveralTracks(buildList {
            trackList.forEach { (_, id) ->
                add(id)
                add("null")
            }
        })
            .testSuccess("Tracks should exist")
            .doOnSuccess { spotifyTracks ->
                withClue("Results should be twice the size of track list") { spotifyTracks.size shouldBe (trackList.size * 2) }

                trackList.forEachIndexed { i, template ->
                    test(template.dataTestName()) {
                        assertNotNull(spotifyTracks[i * 2], "Track should exist", template.testTrack)
                    }

                    test("(Should be null)") {
                        assertNull(spotifyTracks[i * 2 + 1], "Track should NOT exist")
                    }
                }
            }
    }

    // TODO: Get User's Saved Tracks
    // TODO: Save Tracks for Current User
    // TODO: Remove User's Saved Tracks
    // TODO: Check User's Saved Tracks

    context("Get Track Audio Features") {
        withData(trackList) { (_, trackID, _, testAudioFeatures) ->
            api.getTrackAudioFeatures(trackID)
                .testSuccess("Audio Features should exist")
                .doOnSuccess(testAudioFeatures)
        }

        test("Edge Cases") {
            api.getTrackAudioFeatures("0000000000000000000000")
                .testFailure({ "Non Existing ID should return an error, not ${it.print().value}" }) { failure ->
                    assertIs<HttpResult.ClientError>(failure, "Failure should be HttpResult.ClientError")
                    withClue("Response Status should be 404") { failure.response.status shouldBe HttpStatusCode.NotFound }
                }

            api.getTrackAudioFeatures("null")
                .testFailure({ "Malformed ID should return an error, not ${it.print().value}" }) { failure ->
                    assertIs<HttpResult.ClientError>(failure, "Failure should be HttpResult.ClientError")
                    withClue("Response Status should be 400") { failure.response.status shouldBe HttpStatusCode.BadRequest }
                }
        }
    }

    context("Get Several Tracks Audio Features") {
        api.getSeveralTracksAudioFeatures(buildList {
            trackList.forEach { (_, id) ->
                add(id)
                add("null")
            }
        })
            .testSuccess("Tracks Audio Features should exist")
            .doOnSuccess { spotifyTracks ->
                withClue("Results should be twice the size of track list") { spotifyTracks.size shouldBe (trackList.size * 2) }

                trackList.forEachIndexed { i, template ->
                    test(template.dataTestName()) {
                        assertNotNull(spotifyTracks[i * 2], "Audio Features should exist", template.testAudioFeatures)
                    }

                    test("(Should be null)") {
                        assertNull(spotifyTracks[i * 2 + 1], "Audio Features should NOT exist")
                    }
                }
            }
    }

    context("Get Track Audio Analysis") {
        withData(trackList) { (_, trackID, _, _, testAudioAnalysis) ->
            api.getTrackAudioAnalysis(trackID)
                .testSuccess("Audio Analysis should exist")
                .doOnSuccess(testAudioAnalysis)
        }

        test("Edge Cases") {
            api.getTrackAudioAnalysis("0000000000000000000000")
                .testFailure({ "Non Existing ID should return an error, not ${it.print().value}" }) { failure ->
                    assertIs<HttpResult.ClientError>(failure, "Failure should be HttpResult.ClientError")
                    withClue("Response Status should be 404") { failure.response.status shouldBe HttpStatusCode.NotFound }
                }

            api.getTrackAudioAnalysis("null")
                .testFailure({ "Malformed ID should return an error, not ${it.print().value}" }) { failure ->
                    assertIs<HttpResult.ClientError>(failure, "Failure should be HttpResult.ClientError")
                    withClue("Response Status should be 400") { failure.response.status shouldBe HttpStatusCode.BadRequest }
                }
        }
    }

    test("Get Recommendations") {
        api.getRecommendations {
            limit = 20

            seedTracks(trackList.map(SpotifyTrackTestData::id))
            seedGenre("classical")

            energy {
                min = 0.2
                target = 0.8
            }

            danceability {
                min = 0.2
                max = 0.8
            }

            speechiness {
                max = 0.2
            }
        }.testSuccess("Recommendations should exist") {
            it.shouldBe(
                testSeeds = listShouldTestExactly<SpotifyTrackRecommendationSeedObject>(
                    buildList {
                        trackList.forEach { track ->
                            add {
                                shouldBe(
                                    testAfterFilteringSize = beGreaterThan(200),
                                    testAfterRelinkingSize = beGreaterThan(200),
                                    testID = track.id,
                                    testInitialPoolSize = beGreaterThan(200),
                                    testType = EnumSpotifyType.Track
                                )
                            }
                        }

                        add {
                            shouldBe(
                                testAfterFilteringSize = beGreaterThan(10),
                                testAfterRelinkingSize = beGreaterThan(10),
                                testID = "classical",
                                testInitialPoolSize = beGreaterThan(200),
                                testType = EnumSpotifyType.Genre
                            )
                        }
                    }
                ),
                testTracks = haveSize<SpotifyTrack>(20)
            )
        }

        api.getRecommendations(seedTracks = listOf("0000000000000000000000"))
            .testSuccess("Non Existing ID should still return a successful result") {
                it.shouldBe(
                    testSeeds = listShouldTestExactly<SpotifyTrackRecommendationSeedObject> {
                        shouldBe(
                            testInitialPoolSize = 0,
                            testAfterFilteringSize = 0,
                            testAfterRelinkingSize = 0,
                            testType = EnumSpotifyType.Track
                        )
                    },
                    testTracks = beEmpty<SpotifyTrack>()
                )
            }

        api.getRecommendations(seedTracks = listOf("null"))
            .testFailure { "Malformed seed track should return an error, not ${it.print().value}" }

        api.getRecommendations(seedGenres = listOf("null"))
            .testSuccess("Unknown genre should still return a successful result") {
                it.shouldBe(
                    testSeeds = listShouldTestExactly<SpotifyTrackRecommendationSeedObject> {
                        shouldBe(
                            testInitialPoolSize = 0,
                            testAfterFilteringSize = 0,
                            testAfterRelinkingSize = 0,
                            testType = EnumSpotifyType.Genre
                        )
                    },
                    testTracks = beEmpty<SpotifyTrack>()
                )
            }

        api.getRecommendations(seedArtists = listOf("0000000000000000000000"))
            .testSuccess("Non Existing ID should still return a successful result") {
                it.shouldBe(
                    testSeeds = listShouldTestExactly<SpotifyTrackRecommendationSeedObject> {
                        shouldBe(
                            testInitialPoolSize = 0,
                            testAfterFilteringSize = 0,
                            testAfterRelinkingSize = 0,
                            testType = EnumSpotifyType.Artist
                        )
                    },
                    testTracks = beEmpty<SpotifyTrack>()
                )
            }

        api.getRecommendations(seedArtists = listOf("null"))
            .testFailure { "Malformed seed track should return an error, not ${it.print().value}" }
    }
})