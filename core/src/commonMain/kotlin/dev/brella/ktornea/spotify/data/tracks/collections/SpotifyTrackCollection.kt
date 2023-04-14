package dev.brella.ktornea.spotify.data.tracks.collections

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackCollection(val tracks: List<SpotifyTrack>) : Iterable<SpotifyTrack> by tracks