package dev.brella.ktornea.spotify.data.albums

import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalUrls
import dev.brella.ktornea.spotify.data.types.EnumSpotifyArtistType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifySimplifiedArtist(
    @SerialName("external_urls")
    val externalUrls: SpotifyExternalUrls,

    val href: String,

    val id: String,

    val name: String,

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    val type: EnumSpotifyArtistType,

    val uri: String,
)