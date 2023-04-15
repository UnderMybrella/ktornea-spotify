package dev.brella.ktornea.spotify

import dev.brella.ktornea.spotify.data.enums.EnumModality
import dev.brella.ktornea.spotify.data.enums.EnumPitchClass
import dev.brella.ktornea.spotify.data.shouldBe
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import io.kotest.matchers.doubles.between

internal fun SpotifyTrackAudioFeatures.shouldBeGangnamStyle() =
    shouldBe(
        testDanceability = between(0.7, 0.8, 0.0),
        testKey = EnumPitchClass.B,
        testDurationMillis = 219493,
        testTimeSignature = 4
    )

internal fun SpotifyTrackAudioFeatures.shouldBeCallMeMaybe() =
    shouldBe(
        testEnergy = between(0.5, 0.6, 0.0),
        testLoudness = between(-6.6, -6.5, 0.0),
        testMode = EnumModality.Minor,
        testTempo = between(120.0, 120.1, 0.0)
    )

internal fun SpotifyTrackAudioFeatures.shouldBeALongFall() =
    shouldBe(
        testSpeechiness = between(0.0, 0.1, 0.0),
        testAcousticness = between(0.0, 0.1, 0.0),
        testValence = between(0.4, 0.5, 0.0),
        testLiveness = between(0.0, 0.1, 0.0)
    )