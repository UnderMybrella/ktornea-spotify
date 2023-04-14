package dev.brella.ktornea.spotify.data.tracks.collections

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioFeatureCollection(
    @SerialName("audio_features")
    val audioFeatures: List<SpotifyTrackAudioFeature>,
) : Iterable<SpotifyTrackAudioFeature> by audioFeatures