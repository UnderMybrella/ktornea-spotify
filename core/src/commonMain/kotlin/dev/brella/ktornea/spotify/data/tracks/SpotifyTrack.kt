package dev.brella.ktornea.spotify.data.tracks

import dev.brella.ktornea.spotify.data.albums.SpotifyAlbum
import dev.brella.ktornea.spotify.data.types.EnumSpotifyTypeOfTrack
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrack(
    /** The album on which the track appears.
     *  The album object includes a link in href to full information about the album. */
    val album: SpotifyAlbum,

    /** The artists who performed the track.
     *  Each artist object includes a link in href to more detailed information about the artist. */
    val artists: List<SpotifyArtist>,

    /** A list of the countries in which the track can be played, identified by their ISO 3166-1 alpha-2 code. */
    @SerialName("available_markets")
    val availableMarkets: List<String>,

    /** The disc number (usually 1 unless the album consists of more than one disc). */
    @SerialName("disc_number")
    val discNumber: Int,

    /** The track length in milliseconds. */
    @SerialName("duration_ms")
    val durationMillis: Int,

    /** Whether or not the track has explicit lyrics ( true = yes it does; false = no it does not OR unknown). */
    val explicit: Boolean,

    /** Known external IDs for the track. */
    @SerialName("external_ids")
    val externalIDs: SpotifyExternalIDs,

    /** Known external URLs for this track. */
    @SerialName("external_urls")
    val externalUrls: SpotifyExternalUrls,

    /** A link to the Web API endpoint providing full details of the track. */
    val href: String,

    /** The [Spotify ID](https://developer.spotify.com/documentation/web-api/concepts/spotify-uris-ids) for the track. */
    val id: String,

    /** Part of the response when [Track Relinking](https://developer.spotify.com/documentation/web-api/concepts/track-relinking) is applied.
     *  If true, the track is playable in the given market. Otherwise false. */
    @SerialName("is_playable")
    val isPlayable: Boolean? = null,

    /** Part of the response when [Track Relinking](https://developer.spotify.com/documentation/web-api/concepts/track-relinking) is applied, and the requested track has been replaced with different track.
     *  The track in the linked_from object contains information about the originally requested track. */
    @SerialName("linked_from")
    val linkedFrom: SpotifyTrack? = null,

    /** Included in the response when a content restriction is applied. */
    val restrictions: SpotifyRestrictions? = null,

    /** The name of the track. */
    val name: String,

    /** The popularity of the track.
     *  The value will be between 0 and 100, with 100 being the most popular.
     *  The popularity of a track is a value between 0 and 100, with 100 being the most popular.
     *  The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.
     *  Generally speaking, songs that are being played a lot now will have a higher popularity than songs that were played a lot in the past.
     *  Duplicate tracks (e.g. the same track from a single and an album) are rated independently.
     *  Artist and album popularity is derived mathematically from track popularity.
     * ***Note:** the popularity value may lag actual popularity by a few days: the value is not updated in real time.* */
    val popularity: Int,

    /** A link to a 30 second preview (MP3 format) of the track.
     *  Can be null */
    @SerialName("preview_url")
    val previewUrl: String?,

    /** The number of the track.
     *  If an album has several discs, the track number is the number on the specified disc. */
    @SerialName("track_number")
    val trackNumber: Int,

    /** The object type: "track". */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    val type: EnumSpotifyTypeOfTrack,

    /** The Spotify URI for the track. */
    val uri: String,

    /** Whether or not the track is from a local file. */
    @SerialName("is_local")
    val isLocal: Boolean,
)

