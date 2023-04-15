package dev.brella.ktornea.spotify.data

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import dev.brella.ktornea.spotify.testIfNotNullShouldBe
import io.kotest.assertions.assertSoftly

internal fun SpotifyTrackAudioFeatures.shouldBe(
    testAcousticness: Any? = null,
    testAnalysisUrl: Any? = null,
    testDanceability: Any? = null,
    testDurationMillis: Any? = null,
    testEnergy: Any? = null,
    testID: Any? = null,
    testInstrumentalness: Any? = null,
    testKey: Any? = null,
    testLiveness: Any? = null,
    testLoudness: Any? = null,
    testMode: Any? = null,
    testSpeechiness: Any? = null,
    testTempo: Any? = null,
    testTimeSignature: Any? = null,
    testTrackHref: Any? = null,
    testType: Any? = null,
    testUri: Any? = null,
    testValence: Any? = null,
) = assertSoftly {
    testAcousticness.testIfNotNullShouldBe("Acousticness", this.acousticness)
    testAnalysisUrl.testIfNotNullShouldBe("Analysis Url", this.analysisUrl)
    testDanceability.testIfNotNullShouldBe("Danceability", this.danceability)
    testDurationMillis.testIfNotNullShouldBe("Duration Millis", this.durationMillis)
    testEnergy.testIfNotNullShouldBe("Energy", this.energy)
    testID.testIfNotNullShouldBe("ID", this.id)
    testInstrumentalness.testIfNotNullShouldBe("Instrumentalness", this.instrumentalness)
    testKey.testIfNotNullShouldBe("Key", this.key)
    testLiveness.testIfNotNullShouldBe("Liveness", this.liveness)
    testLoudness.testIfNotNullShouldBe("Loudness", this.loudness)
    testMode.testIfNotNullShouldBe("Mode", this.mode)
    testSpeechiness.testIfNotNullShouldBe("Speechiness", this.speechiness)
    testTempo.testIfNotNullShouldBe("Tempo", this.tempo)
    testTimeSignature.testIfNotNullShouldBe("Time Signature", this.timeSignature)
    testTrackHref.testIfNotNullShouldBe("Track href", this.trackHref)
    testType.testIfNotNullShouldBe("Type", this.type)
    testUri.testIfNotNullShouldBe("Uri", this.uri)
    testValence.testIfNotNullShouldBe("Valence", this.valence)
}