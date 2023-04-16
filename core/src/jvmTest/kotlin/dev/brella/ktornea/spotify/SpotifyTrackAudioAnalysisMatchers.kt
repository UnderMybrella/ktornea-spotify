package dev.brella.ktornea.spotify

import dev.brella.ktornea.spotify.data.SemanticVersion
import dev.brella.ktornea.spotify.data.enums.EnumModality
import dev.brella.ktornea.spotify.data.enums.EnumPitchClass
import dev.brella.ktornea.spotify.data.shouldBe
import dev.brella.ktornea.spotify.data.tracks.analysis.*
import io.kotest.matchers.doubles.between

public fun SpotifyTrackAudioAnalysis.shouldBeGangnamStyle() =
    shouldBe(
        testMeta = go<SpotifyTrackAudioAnalysisMeta> {
            shouldBe(
                testAnalyserVersion = SemanticVersion(4, 0, 0),
                testPlatform = "Linux",
                testTimestamp = 1587536791,
                testInputProcess = "libvorbisfile L+R 44100->22050"
            )
        },
        testTrack = go<SpotifyTrackAudioAnalysisDetails> {
            shouldBe(
                testNumberOfSamples = 4839828,
                testSampleMD5 = "",
                testOffsetSeconds = 0,
                testWindowSeconds = 0,
                testAnalysisChannels = 1,
                testTimeSignature = 4,
                testMode = EnumModality.Major,
                testCodeVersion = SemanticVersion(3, 1, 5),
                testEchoprintVersion = SemanticVersion(4, 1, 2),
                testSynchronisationVersion = SemanticVersion(1, 0, 0),
                testRhythmVersion = SemanticVersion(1, 0, 0)
            )
        },
        testBars = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(0.8, 0.9, 0.0), between(1.7, 1.8, 0.0), between(0.8, 0.9, 0.0)) },
            { shouldBe(between(2.5, 2.6, 0.0), between(1.7, 1.8, 0.0), between(0.7, 0.8, 0.0)) },
            { shouldBe(between(4.3, 4.4, 0.0), between(1.8, 1.9, 0.0), between(0.4, 0.5, 0.0)) }
        ),
        testBeats = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(0.8, 0.9, 0.0), between(0.4, 0.5, 0.0), between(0.5, 0.6, 0.0)) },
            { shouldBe(between(1.2, 1.3, 0.0), between(0.4, 0.5, 0.0), between(0.3, 0.4, 0.0)) },
            { shouldBe(between(1.7, 1.8, 0.0), between(0.4, 0.5, 0.0), between(0.5, 0.6, 0.0)) }
        ),
        testSections = listShouldStartWithTest<SpotifyTrackAudioAnalysisSection> {
            shouldBe(
                testStart = 0.0,
                testDuration = between(6.6, 6.7, 0.0),
                testConfidence = 1.0,
                testLoudness = between(-15.4, -15.3, 0.0),
                testMode = EnumModality.Major
            )
        },
        testSegments = listShouldStartWithTest<SpotifyTrackAudioAnalysisSegment> {
            shouldBe(
                testStart = 0.0,
                testDuration = between(0.4, 0.5, 0.0),
                testConfidence = 0.0,
                testLoudnessStart = -60.0,
                testPitches = doubleArrayShouldStartWith(
                    between(0.5, 0.6, 0.0),
                    between(0.7, 0.8, 0.0),
                    between(0.8, 0.9, 0.0)
                ),
                testTimbre = doubleArrayShouldStartWith(
                    0.0,
                    between(171.1, 171.2, 0.0),
                    between(9.4, 9.5, 0.0)
                )
            )
        },
        testTatums = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(0.8, 0.9, 0.0), between(0.2, 0.3, 0.0), between(0.5, 0.6, 0.0)) },
            { shouldBe(between(1.0, 1.1, 0.0), between(0.2, 0.3, 0.0), between(0.5, 0.6, 0.0)) },
            { shouldBe(between(1.2, 1.3, 0.0), between(0.2, 0.3, 0.0), between(0.3, 0.4, 0.0)) }
        )
    )
public fun SpotifyTrackAudioAnalysis.shouldBeCallMeMaybe() =
    shouldBe(
        testMeta = go<SpotifyTrackAudioAnalysisMeta> {
            shouldBe(
                testAnalyserVersion = SemanticVersion(4, 0, 0),
                testPlatform = "Linux",
                testTimestamp = 1573922538,
                testInputProcess = "libvorbisfile L+R 44100->22050"
            )
        },
        testTrack = go<SpotifyTrackAudioAnalysisDetails> {
            shouldBe(
                testDuration = between(193.3, 193.5, 0.0),
                testSampleMD5 = "",
                testOffsetSeconds = 0,
                testWindowSeconds = 0,
                testAnalysisChannels = 1,
                testTimeSignature = 4,
                testMode = EnumModality.Minor,
                testCodeVersion = SemanticVersion(3, 1, 5),
                testEchoprintVersion = SemanticVersion(4, 1, 2),
                testSynchronisationVersion = SemanticVersion(1, 0, 0),
                testRhythmVersion = SemanticVersion(1, 0, 0)
            )
        },
        testBars = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(2.2, 2.3, 0.0), between(1.9, 2.0, 0.0), between(0.6, 0.7, 0.0)) },
            { shouldBe(between(4.2, 4.3, 0.0), between(1.9, 2.0, 0.0), between(0.2, 0.3, 0.0)) },
            { shouldBe(between(6.2, 6.3, 0.0), between(1.9, 2.0, 0.0), between(0.4, 0.5, 0.0)) }
        ),
        testBeats = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(0.7, 0.8, 0.0), between(0.4, 0.5, 0.0), between(0.9, 1.0, 0.0)) },
            { shouldBe(between(1.2, 1.3, 0.0), between(0.4, 0.6, 0.0), between(0.9, 1.0, 0.0)) },
            { shouldBe(between(1.7, 1.8, 0.0), between(0.4, 0.6, 0.0), between(0.9, 1.0, 0.0)) }
        ),
        testSections = listShouldStartWithTest<SpotifyTrackAudioAnalysisSection> {
            shouldBe(
                testStart = 0.0,
                testDuration = between(5.2, 5.3, 0.0),
                testConfidence = 1.0,
                testLoudness = between(-22.2, -22.1, 0.0),
                testMode = EnumModality.Minor
            )
        },
        testSegments = listShouldStartWithTest<SpotifyTrackAudioAnalysisSegment> {
            shouldBe(
                testStart = 0.0,
                testDuration = between(0.2, 0.3, 0.0),
                testConfidence = 0.0,
                testLoudnessStart = -60.0,
                testPitches = doubleArrayShouldStartWith(
                    between(0.5, 0.6, 0.0),
                    between(0.1, 0.2, 0.0),
                    between(0.2, 0.3, 0.0)
                ),
                testTimbre = doubleArrayShouldStartWith(
                    0.0,
                    between(171.1, 171.2, 0.0),
                    between(9.4, 9.5, 0.0)
                )
            )
        },
        testTatums = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(0.7, 0.8, 0.0), between(0.2, 0.3, 0.0), between(0.9, 1.0, 0.0)) },
            { shouldBe(between(0.9, 1.0, 0.0), between(0.2, 0.3, 0.0), between(0.9, 1.0, 0.0)) },
            { shouldBe(between(1.2, 1.3, 0.0), between(0.2, 0.3, 0.0), between(0.9, 1.0, 0.0)) }
        )
    )
public fun SpotifyTrackAudioAnalysis.shouldBeALongFall() =
    shouldBe(
        testMeta = go<SpotifyTrackAudioAnalysisMeta> {
            shouldBe(
                testAnalyserVersion = SemanticVersion(4, 0, 0),
                testPlatform = "Linux",
                testTimestamp = 1646246408,
                testInputProcess = "libvorbisfile L+R 44100->22050"
            )
        },
        testTrack = go<SpotifyTrackAudioAnalysisDetails> {
            shouldBe(
                testNumberOfSamples = 6450125,
                testSampleMD5 = "",
                testOffsetSeconds = 0,
                testWindowSeconds = 0,
                testAnalysisChannels = 1,
                testTimeSignature = 4,
                testKey = EnumPitchClass.A,
                testCodeVersion = SemanticVersion(3, 1, 5),
                testEchoprintVersion = SemanticVersion(4, 1, 2),
                testSynchronisationVersion = SemanticVersion(1, 0, 0),
                testRhythmVersion = SemanticVersion(1, 0, 0)
            )
        },
        testBars = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(3.7, 3.8, 0.0), between(0.3, 0.4, 0.0), between(0.6, 0.7, 0.0)) },
            { shouldBe(between(4.0, 4.1, 0.0), between(1.4, 1.5, 0.0), between(0.3, 0.4, 0.0)) },
            { shouldBe(between(5.4, 5.5, 0.0), between(1.4, 1.5, 0.0), between(0.3, 0.4, 0.0)) }
        ),
        testBeats = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(3.3, 3.4, 0.0), between(0.3, 0.4, 0.0), between(0.8, 0.9, 0.0)) },
            { shouldBe(between(3.7, 3.8, 0.0), between(0.3, 0.4, 0.0), between(0.8, 0.9, 0.0)) },
            { shouldBe(between(4.0, 4.1, 0.0), between(0.3, 0.4, 0.0), between(0.8, 0.9, 0.0)) }
        ),
        testSections = listShouldStartWithTest<SpotifyTrackAudioAnalysisSection> {
            shouldBe(
                testStart = 0.0,
                testDuration = between(9.3, 9.4, 0.0),
                testConfidence = 1.0,
                testLoudness = between(-8.9, -8.8, 0.0),
                testKey = EnumPitchClass.D
            )
        },
        testSegments = listShouldStartWithTest<SpotifyTrackAudioAnalysisSegment> {
            shouldBe(
                testStart = 0.0,
                testDuration = between(3.3, 3.4, 0.0),
                testConfidence = 0.0,
                testLoudnessStart = -60.0,
                testPitches = doubleArrayShouldStartWith(
                    between(0.7, 0.8, 0.0),
                    between(0.7, 0.8, 0.0),
                    between(0.8, 0.9, 0.0)
                ),
                testTimbre = doubleArrayShouldStartWith(
                    0.0,
                    between(171.1, 171.2, 0.0),
                    between(9.4, 9.5, 0.0)
                )
            )
        },
        testTatums = listShouldStartWithTests<SpotifyTrackAudioAnalysisInterval>(
            { shouldBe(between(3.3, 3.4, 0.0), between(0.1, 0.2, 0.0), between(0.8, 0.9, 0.0)) },
            { shouldBe(between(3.5, 3.6, 0.0), between(0.1, 0.2, 0.0), between(0.8, 0.9, 0.0)) },
            { shouldBe(between(3.7, 3.8, 0.0), between(0.1, 0.2, 0.0), between(0.8, 0.9, 0.0)) }
        )
    )