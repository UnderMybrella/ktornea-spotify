package dev.brella.ktornea.spotify.data.tracks.analysis

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyAudioAnalysis(
    val meta: SpotifyAudioAnalysisMeta,
    val track: SpotifyAudioAnalysisTrack
)
