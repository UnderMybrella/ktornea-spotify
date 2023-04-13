package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyImage(
    val url: String,
    val height: Int,
    val width: Int
)