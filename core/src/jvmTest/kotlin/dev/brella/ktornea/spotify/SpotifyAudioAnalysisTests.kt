package dev.brella.ktornea.spotify

import dev.brella.kornea.errors.common.doOnFailure
import dev.brella.kornea.errors.common.getOrThrow
import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
import dev.brella.ktornea.spotify.data.tracks.analysis.SpotifyAudioAnalysis
import dev.brella.ktornea.spotify.data.tracks.analysis.SpotifyAudioAnalysisMeta
import dev.brella.ktornea.spotify.data.tracks.analysis.SpotifyAudioAnalysisTrack
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpotifyAudioAnalysisTests {
    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val client = HttpClient(CIO) {
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

    @Test
    fun testMeta() = runBlocking {
        arrayOf(
            "11dFghVXANMlKmJXsNCbNl",
            "03xB2RaR17TyaR7tAfOYde",
            "2zpQSMe7xEFeSdGNoFLWl8",
            "2fAIfPLrPUTW1AmJRR428Q",
            "5ICLsHla4t5dReobgkL5vA",
            "4TO5chEVULiyd3o0F7mHp0",
            "7wHpjhpBhiabSKRPJgO2im"
        )
            .forEach { id ->
                val analysis =
                    client.get("https://api.spotify.com/v1/audio-analysis/$id")
                        .body<SpotifyAudioAnalysis>()

                println(json.encodeToString(analysis.))
            }

    }
}