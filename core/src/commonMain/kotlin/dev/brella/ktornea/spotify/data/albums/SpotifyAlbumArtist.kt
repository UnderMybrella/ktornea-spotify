package dev.brella.ktornea.spotify.data.albums

import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyAlbumArtist(
    @SerialName("external_urls")
    val externalUrls: SpotifyExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)