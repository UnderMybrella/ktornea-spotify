package dev.brella.ktornea.spotify.data

import dev.brella.ktornea.http.HttpStatusCodeFromInt
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyErrorResponse(
    val error: SpotifyError
)

@Serializable
public data class SpotifyError (
    val status: HttpStatusCodeFromInt,
    val message: String
)
