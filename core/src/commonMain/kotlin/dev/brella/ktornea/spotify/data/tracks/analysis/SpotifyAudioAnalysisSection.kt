package dev.brella.ktornea.spotify.data.tracks.analysis

import dev.brella.ktornea.spotify.data.enums.EnumPitchClass
import dev.brella.ktornea.spotify.data.types.SpotifyHasTempo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyAudioAnalysisSection(
    /** The starting point (in seconds) of the section. */
    val start: Double,

    /** The duration (in seconds) of the section. */
    val duration: Double,

    /** The confidence, from 0.0 to 1.0, of the reliability of the section's "designation". */
    val confidence: Double,

    /** The overall loudness of the section in decibels (dB). Loudness values are useful for comparing relative loudness of sections within tracks. */
    val loudness: Double,

    /** The overall estimated tempo of the section in beats per minute (BPM). In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration. */
    override val tempo: Double,

    /** The confidence, from 0.0 to 1.0, of the reliability of the tempo. Some tracks contain tempo changes or sounds which don't contain tempo (like pure speech) which would correspond to a low value in this field. */
    @SerialName("tempo_confidence")
    override val tempoConfidence: Double,
    val key: EnumPitchClass,
    @SerialName("key_confidence")
    val keyConfidence: Double,
    val mode: Double,
    @SerialName("mode_confidence")
    val modeConfidence: Double,

    @SerialName("time_signature")
    val timeSignature: Int,
    @SerialName("time_signature_confidence")
    val timeSignatureConfidence: Double
): SpotifyHasTempo
