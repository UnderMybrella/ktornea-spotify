package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyImage(
    val url: String,
    val height: Int,
    val width: Int
)