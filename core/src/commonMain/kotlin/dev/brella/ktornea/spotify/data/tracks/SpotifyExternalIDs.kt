package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyExternalIDs(
    val isrc: String? = null,
    val ean: String? = null,
    val upc: String? = null
)