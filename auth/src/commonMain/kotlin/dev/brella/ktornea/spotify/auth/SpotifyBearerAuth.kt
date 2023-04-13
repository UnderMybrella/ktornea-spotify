package dev.brella.ktornea.spotify.auth

import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.spotify.data.auth.submitSpotifyClientCredentialsFlow
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*

public fun Auth.spotifyClientCredentials(clientID: () -> String, clientSecret: () -> String) {
    bearer {
        refreshTokens {
            client.submitSpotifyClientCredentialsFlow(clientID(), clientSecret()) { markAsRefreshTokenRequest() }
                .map { (accessToken) -> BearerTokens(accessToken, "") }
                .getOrNull()
        }

        sendWithoutRequest { request -> request.url.host == "api.spotify.com" }
    }
}