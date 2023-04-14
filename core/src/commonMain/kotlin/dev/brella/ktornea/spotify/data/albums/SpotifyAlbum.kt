package dev.brella.ktornea.spotify.data.albums

import dev.brella.ktornea.spotify.data.SpotifyCopyright
import dev.brella.ktornea.spotify.data.enums.EnumDatePrecision
import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalIDs
import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalUrls
import dev.brella.ktornea.spotify.data.tracks.SpotifyImage
import dev.brella.ktornea.spotify.data.tracks.SpotifyRestrictions
import dev.brella.ktornea.spotify.data.types.EnumSpotifyAlbumGroup
import dev.brella.ktornea.spotify.data.types.EnumSpotifyAlbumType
import dev.brella.ktornea.spotify.data.types.EnumSpotifyTypeOfAlbum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyAlbum(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName("album_group")
    val albumGroup: EnumSpotifyAlbumGroup? = null,

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName("album_type")
    val albumType: EnumSpotifyAlbumType,

    val artists: List<SpotifySimplifiedArtist>,

    @SerialName("available_markets")
    val availableMarkets: List<String>,

    val copyrights: SpotifyCopyright? = null,

    @SerialName("external_ids")
    val externalIDs: SpotifyExternalIDs? = null,

    @SerialName("external_urls")
    val externalUrls: SpotifyExternalUrls,

    val genres: List<String>? = null,

    val href: String,

    val id: String,

    val images: List<SpotifyImage>,

    val label: String? = null,

    val name: String,

    val popularity: Int? = null,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("release_date_precision")
    val releaseDatePrecision: EnumDatePrecision,

    val restrictions: SpotifyRestrictions? = null,

    @SerialName("total_tracks")
    val totalTracks: Int,

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    val type: EnumSpotifyTypeOfAlbum,

    val uri: String,
)