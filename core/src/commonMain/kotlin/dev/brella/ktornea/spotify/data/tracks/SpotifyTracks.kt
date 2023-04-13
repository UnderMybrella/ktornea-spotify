package dev.brella.ktornea.spotify.data.tracks

import kotlinx.serialization.Serializable

@Serializable
data class SpotifyTracks(val tracks: List<SpotifyTrack>)