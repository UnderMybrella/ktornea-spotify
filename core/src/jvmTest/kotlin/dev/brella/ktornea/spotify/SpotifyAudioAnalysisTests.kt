package dev.brella.ktornea.spotify

import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
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
import kotlin.test.Test

class SpotifyAudioAnalysisTests {
    private val client = HttpClient(CIO) {
        install(ContentEncoding) {
            gzip()
            deflate()
            identity()
        }

        install(ContentNegotiation) {
            json()
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
                    client.submitSpotifyClientCredentialsFlow(
                        System.getenv("CLIENT_ID"),
                        System.getenv("CLIENT_SECRET")
                    ) { markAsRefreshTokenRequest() }
                        .map { (accessToken) -> BearerTokens(accessToken, "") }
                        .getOrNull()
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
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        arrayOf(
            "11dFghVXANMlKmJXsNCbNl",
            "03xB2RaR17TyaR7tAfOYde",
            "2zpQSMe7xEFeSdGNoFLWl8",
            "2fAIfPLrPUTW1AmJRR428Q",
            "5ICLsHla4t5dReobgkL5vA",
            "4TO5chEVULiyd3o0F7mHp0"
        )
            .forEach { id ->
                val meta = json.decodeFromJsonElement<SpotifyAudioAnalysisTrack>(
                    client.get("https://api.spotify.com/v1/audio-analysis/$id")
                        .body<JsonObject>()
                        .getValue("track")
                )

                println(json.encodeToString(meta))
            }

    }
}