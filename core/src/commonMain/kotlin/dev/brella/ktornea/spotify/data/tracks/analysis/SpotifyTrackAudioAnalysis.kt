package dev.brella.ktornea.spotify.data.tracks.analysis

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioAnalysis(
    val meta: SpotifyTrackAudioAnalysisMeta,
    val track: SpotifyTrackAudioAnalysisDetails,
    val bars: List<SpotifyTrackAudioAnalysisInterval>,
    val beats: List<SpotifyTrackAudioAnalysisInterval>,
    val sections: List<SpotifyTrackAudioAnalysisSection>,
    val segments: List<SpotifyTrackAudioAnalysisSegment>,
    val tatums: List<SpotifyTrackAudioAnalysisInterval>,
)
