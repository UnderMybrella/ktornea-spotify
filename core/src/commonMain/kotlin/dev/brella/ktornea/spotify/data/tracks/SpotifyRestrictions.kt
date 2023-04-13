package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyRestrictions(
    val reason: String
)