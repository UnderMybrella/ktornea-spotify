package dev.brella.ktornea.spotify.data.auth

import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.results.wrapInResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyClientCredentials(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("token_type")
    val tokenType: String,

    @SerialName("expires_in")
    val expiresIn: Long,
)

public suspend inline fun HttpClient.submitSpotifyClientCredentialsFlow(
    clientID: String,
    clientSecret: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {},
): KorneaResult<SpotifyClientCredentials> =
    wrapInResult(submitForm(
        url = "https://accounts.spotify.com/api/token",
        formParameters = parametersOf("grant_type", "client_credentials")
    ) {
        basicAuth(clientID, clientSecret)

        block()
    }).map { response -> response.body<SpotifyClientCredentials>() }
