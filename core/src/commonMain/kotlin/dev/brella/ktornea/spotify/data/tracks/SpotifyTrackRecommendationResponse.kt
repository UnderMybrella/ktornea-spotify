package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

/** A set of recommendations */
@Serializable
public data class SpotifyTrackRecommendationResponse(
    /** An array of recommendation seed objects. */
    val seeds: List<SpotifyTrackRecommendationSeedObject>,

    /** An array of track object (simplified) ordered according to the parameters supplied. */
    val tracks: List<SpotifyTrack>,
)
