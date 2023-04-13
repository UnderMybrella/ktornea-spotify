package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyArtist(
    @SerialName("external_urls")
    val externalUrls: SpotifyExternalUrls,
    val followers: SpotifyArtistFollowers? = null,
    val genres: List<String> = emptyList(),
    val href: String,
    val id: String,
    val images: List<SpotifyImage> = emptyList(),
    val name: String,
    val popularity: Int = 0,
    val type: String,
    val uri: String
)