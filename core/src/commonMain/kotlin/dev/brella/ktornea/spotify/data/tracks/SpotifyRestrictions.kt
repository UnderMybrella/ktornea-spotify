package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyRestrictions(
    val reason: String
)