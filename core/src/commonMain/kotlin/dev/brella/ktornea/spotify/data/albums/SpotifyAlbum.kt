package dev.brella.ktornea.spotify.data.albums

import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalUrls
import dev.brella.ktornea.spotify.data.tracks.SpotifyImage
import dev.brella.ktornea.spotify.data.tracks.SpotifyRestrictions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyAlbum(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("total_tracks")
    val totalTracks: Int,
    @SerialName("available_markets")
    val availableMarkets: List<String>,
    @SerialName("external_urls")
    val externalUrls: SpotifyExternalUrls,
    val href: String,
    val id: String,
    val images: List<SpotifyImage>,
    val name: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String,
    val restrictions: SpotifyRestrictions? = null,
    val type: String,
    val uri: String,
    @SerialName("album_group")
    val albumGroup: String? = null,
    val artists: List<SpotifyAlbumArtist>
)