package dev.brella.ktornea.spotify.data.tracks

import dev.brella.ktornea.spotify.data.enums.EnumSpotifyType
import dev.brella.ktornea.spotify.data.types.EnumSpotifySeedType
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackRecommendationSeedObject(
    /** The number of tracks available after min_* and max_* filters have been applied. */
    val afterFilteringSize: Int,

    /** The number of tracks available after relinking for regional availability. */
    val afterRelinkingSize: Int,

    /** A link to the full track or artist data for this seed.
     *
     *  For tracks this will be a link to a Track Object.
     *  For artists a link to an Artist Object. For genre seeds, this value will be null. */
    val href: String?,

    /** The id used to select this seed.
     *
     *  This will be the same as the string used in the seed_artists, seed_tracks or seed_genres parameter. */
    val id: String,

    /** The number of recommended tracks available for this seed. */
    val initialPoolSize: Int,

    /** The entity type of this seed. One of artist, [track][EnumSpotifyType.Track] or genre. */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    val type: EnumSpotifySeedType
)
