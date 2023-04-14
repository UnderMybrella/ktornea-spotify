package dev.brella.ktornea.spotify.data.tracks.analysis

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioAnalysisInterval(
    /** The starting point (in seconds) of the time interval. */
    val start: Double,

    /** The duration (in seconds) of the time interval. */
    val duration: Double,

    /** The confidence, from 0.0 to 1.0, of the reliability of the interval. */
    val confidence: Double
)