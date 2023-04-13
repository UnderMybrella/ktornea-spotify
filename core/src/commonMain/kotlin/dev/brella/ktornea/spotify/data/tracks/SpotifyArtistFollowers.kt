package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyArtistFollowers(
    val href: String?,
    val total: Int
)