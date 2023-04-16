package dev.brella.ktornea.spotify.data

import dev.brella.ktornea.spotify.data.tracks.analysis.*
import dev.brella.ktornea.spotify.testIfNotNullShouldBe
import io.kotest.assertions.assertSoftly

public fun SpotifyTrackAudioAnalysis.shouldBe(
    testMeta: Any? = null,
    testTrack: Any? = null,
    testBars: Any? = null,
    testBeats: Any? = null,
    testSections: Any? = null,
    testSegments: Any? = null,
    testTatums: Any? = null,
) = assertSoftly {
    testMeta.testIfNotNullShouldBe("Meta", this.meta)
    testTrack.testIfNotNullShouldBe("Track", this.track)
    testBars.testIfNotNullShouldBe("Bars", this.bars)
    testBeats.testIfNotNullShouldBe("Beats", this.beats)
    testSections.testIfNotNullShouldBe("Sections", this.sections)
    testSegments.testIfNotNullShouldBe("Segments", this.segments)
    testTatums.testIfNotNullShouldBe("Tatums", this.tatums)
}

public fun SpotifyTrackAudioAnalysisMeta.shouldBe(
    testAnalyserVersion: Any? = null,
    testPlatform: Any? = null,
    testDetailedStatus: Any? = null,
    testStatusCode: Any? = null,
    testTimestamp: Any? = null,
    testAnalysisTime: Any? = null,
    testInputProcess: Any? = null
) = assertSoftly {
    testAnalyserVersion.testIfNotNullShouldBe("Analyser Version", this.analyserVersion)
    testPlatform.testIfNotNullShouldBe("Platform", this.platform)
    testDetailedStatus.testIfNotNullShouldBe("Detailed Status", this.detailedStatus)
    testStatusCode.testIfNotNullShouldBe("Status Code", this.statusCode)
    testTimestamp.testIfNotNullShouldBe("Timestamp", this.timestamp)
    testAnalysisTime.testIfNotNullShouldBe("Analysis Time", this.analysisTime)
    testInputProcess.testIfNotNullShouldBe("Input Process", this.inputProcess)
}

public fun SpotifyTrackAudioAnalysisDetails.shouldBe(
    testNumberOfSamples: Any? = null,
    testDuration: Any? = null,
    testSampleMD5: Any? = null,
    testOffsetSeconds: Any? = null,
    testWindowSeconds: Any? = null,
    testAnalysisSampleRate: Any? = null,
    testAnalysisChannels: Any? = null,
    testEndOfFadeIn: Any? = null,
    testStartOfFadeOut: Any? = null,
    testLoudness: Any? = null,
    testTempo: Any? = null,
    testTempoConfidence: Any? = null,
    testTimeSignature: Any? = null,
    testTimeSignatureConfidence: Any? = null,
    testKey: Any? = null,
    testKeyConfidence: Any? = null,
    testMode: Any? = null,
    testModeConfidence: Any? = null,
    testCodeString: Any? = null,
    testCodeVersion: Any? = null,
    testEchoprintString: Any? = null,
    testEchoprintVersion: Any? = null,
    testSynchronisationString: Any? = null,
    testSynchronisationVersion: Any? = null,
    testRhythmString: Any? = null,
    testRhythmVersion: Any? = null
) = assertSoftly {
    testNumberOfSamples.testIfNotNullShouldBe("Number of Samples", this.numberOfSamples)
    testDuration.testIfNotNullShouldBe("Duration", this.duration)
    testSampleMD5.testIfNotNullShouldBe("Sample MD5", this.sampleMD5)
    testOffsetSeconds.testIfNotNullShouldBe("Offset Seconds", this.offsetSeconds)
    testWindowSeconds.testIfNotNullShouldBe("Window Seconds", this.windowSeconds)
    testAnalysisSampleRate.testIfNotNullShouldBe("Analysis Sample Rate", this.analysisSampleRate)
    testAnalysisChannels.testIfNotNullShouldBe("Analysis Channels", this.analysisChannels)
    testEndOfFadeIn.testIfNotNullShouldBe("End Of Fade In", this.endOfFadeIn)
    testStartOfFadeOut.testIfNotNullShouldBe("Start Of Fade Out", this.startOfFadeOut)
    testLoudness.testIfNotNullShouldBe("Loudness", this.loudness)
    testTempo.testIfNotNullShouldBe("Tempo", this.tempo)
    testTempoConfidence.testIfNotNullShouldBe("Tempo Confidence", this.tempoConfidence)
    testTimeSignature.testIfNotNullShouldBe("Time Signature", this.timeSignature)
    testTimeSignatureConfidence.testIfNotNullShouldBe("Time Signature Confidence", this.timeSignatureConfidence)
    testKey.testIfNotNullShouldBe("Key", this.key)
    testKeyConfidence.testIfNotNullShouldBe("Key Confidence", this.keyConfidence)
    testMode.testIfNotNullShouldBe("Mode", this.mode)
    testModeConfidence.testIfNotNullShouldBe("Mode Confidence", this.modeConfidence)
    testCodeString.testIfNotNullShouldBe("Code String", this.codeString)
    testCodeVersion.testIfNotNullShouldBe("Code Version", this.codeVersion)
    testEchoprintString.testIfNotNullShouldBe("Echoprint String", this.echoprintString)
    testEchoprintVersion.testIfNotNullShouldBe("Echoprint Version", this.echoprintVersion)
    testSynchronisationString.testIfNotNullShouldBe("Synchronisation String", this.synchronisationString)
    testSynchronisationVersion.testIfNotNullShouldBe("Synchronisation Version", this.synchronisationVersion)
    testRhythmString.testIfNotNullShouldBe("Rhythm String", this.rhythmString)
    testRhythmVersion.testIfNotNullShouldBe("Rhythm Version", this.rhythmVersion)
}

public fun SpotifyTrackAudioAnalysisInterval.shouldBe(
    testStart: Any? = null,
    testDuration: Any? = null,
    testConfidence: Any? = null
) = assertSoftly {
    testStart.testIfNotNullShouldBe("Start", this.start)
    testDuration.testIfNotNullShouldBe("Duration", this.duration)
    testConfidence.testIfNotNullShouldBe("Confidence", this.confidence)
}

public fun SpotifyTrackAudioAnalysisSection.shouldBe(
    testStart: Any? = null,
    testDuration: Any? = null,
    testConfidence: Any? = null,
    testLoudness: Any? = null,
    testTempo: Any? = null,
    testTempoConfidence: Any? = null,
    testKey: Any? = null,
    testKeyConfidence: Any? = null,
    testMode: Any? = null,
    testModeConfidence: Any? = null,
    testTimeSignature: Any? = null,
    testTimeSignatureConfidence: Any? = null
) = assertSoftly {
    testStart.testIfNotNullShouldBe("Start", this.start)
    testDuration.testIfNotNullShouldBe("Duration", this.duration)
    testConfidence.testIfNotNullShouldBe("Confidence", this.confidence)
    testLoudness.testIfNotNullShouldBe("Loudness", this.loudness)
    testTempo.testIfNotNullShouldBe("Tempo", this.tempo)
    testTempoConfidence.testIfNotNullShouldBe("Tempo Confidence", this.tempoConfidence)
    testKey.testIfNotNullShouldBe("Key", this.key)
    testKeyConfidence.testIfNotNullShouldBe("Key Confidence", this.keyConfidence)
    testMode.testIfNotNullShouldBe("Mode", this.mode)
    testModeConfidence.testIfNotNullShouldBe("Mode Confidence", this.modeConfidence)
    testTimeSignature.testIfNotNullShouldBe("Time Signature", this.timeSignature)
    testTimeSignatureConfidence.testIfNotNullShouldBe("Time Signature Confidence", this.timeSignatureConfidence)
}

public fun SpotifyTrackAudioAnalysisSegment.shouldBe(
    testStart: Any? = null,
    testDuration: Any? = null,
    testConfidence: Any? = null,
    testLoudnessStart: Any? = null,
    testLoudnessMax: Any? = null,
    testLoudnessMaxTime: Any? = null,
    testLoudnessEnd: Any? = null,
    testPitches: Any? = null,
    testTimbre: Any? = null
) = assertSoftly {
    testStart.testIfNotNullShouldBe("Start", this.start)
    testDuration.testIfNotNullShouldBe("Duration", this.duration)
    testConfidence.testIfNotNullShouldBe("Confidence", this.confidence)
    testLoudnessStart.testIfNotNullShouldBe("Loudness Start", this.loudnessStart)
    testLoudnessMax.testIfNotNullShouldBe("Loudness Max", this.loudnessMax)
    testLoudnessMaxTime.testIfNotNullShouldBe("Loudness Max Time", this.loudnessMaxTime)
    testLoudnessEnd.testIfNotNullShouldBe("Loudness End", this.loudnessEnd)
    testPitches.testIfNotNullShouldBe("Pitches", this.pitches)
    testTimbre.testIfNotNullShouldBe("Timbre", this.timbre)
}