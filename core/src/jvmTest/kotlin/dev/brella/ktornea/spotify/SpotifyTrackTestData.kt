package dev.brella.ktornea.spotify

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import io.kotest.datatest.WithDataTestName

data class SpotifyTrackTestData(
    val name: String,
    val id: String,
    val testTrack: (SpotifyTrack) -> Unit,
    val testAudioFeatures: (SpotifyTrackAudioFeatures) -> Unit
): WithDataTestName {
    override fun dataTestName(): String = "$name ($id)"
}
