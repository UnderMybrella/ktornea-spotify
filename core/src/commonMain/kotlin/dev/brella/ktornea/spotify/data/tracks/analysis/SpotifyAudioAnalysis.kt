package dev.brella.ktornea.spotify.data.tracks.analysis

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyAudioAnalysis(
    val meta: SpotifyAudioAnalysisMeta,
    val track: SpotifyAudioAnalysisTrack,
    val bars: List<SpotifyAudioAnalysisInterval>,
    val beats: List<SpotifyAudioAnalysisInterval>,
    val sections: List<SpotifyAudioAnalysisSection>,
    val tatums: List<SpotifyAudioAnalysisInterval>,
)
