package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioFeatures(
    @SerialName("audio_features")
    val audioFeatures: List<SpotifyTrackAudioFeature>,
)
