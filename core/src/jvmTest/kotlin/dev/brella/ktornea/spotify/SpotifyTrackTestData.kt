package dev.brella.ktornea.spotify

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import io.kotest.datatest.WithDataTestName

data class SpotifyTrackTestData(
    val name: String,
    val id: String,
    val test: (SpotifyTrack) -> Unit
): WithDataTestName {
    override fun dataTestName(): String = "$name ($id)"
}
