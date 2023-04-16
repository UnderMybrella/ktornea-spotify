package dev.brella.ktornea.spotify.data.tracks.collections

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioFeatureCollection(
    @SerialName("audio_features")
    val audioFeatures: List<SpotifyTrackAudioFeatures?>,
) : Iterable<SpotifyTrackAudioFeatures?> by audioFeatures