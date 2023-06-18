package dev.brella.ktornea.spotify.auth

import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.forms.*
import io.ktor.http.auth.*

@Suppress("FunctionName")
public fun HttpClientConfig<*>.SpotifyClientCredentials(clientID: () -> String, clientSecret: () -> String) =
    install(Auth) { spotifyClientCredentials(clientID, clientSecret) }

public fun Auth.spotifyClientCredentials(clientID: () -> String, clientSecret: () -> String) {
    bearer {
        realm = "spotify"

        refreshTokens {
            client.submitSpotifyClientCredentialsFlow(clientID(), clientSecret()) { markAsRefreshTokenRequest() }
                .map { (accessToken) -> BearerTokens(accessToken, "") }
                .getOrNull()
        }

        sendWithoutRequest { request -> request.url.host == "api.spotify.com" }
    }
}