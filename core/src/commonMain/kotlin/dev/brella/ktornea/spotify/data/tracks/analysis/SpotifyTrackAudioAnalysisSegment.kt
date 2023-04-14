package dev.brella.ktornea.spotify.data.tracks.analysis

import dev.brella.kornea.annotations.DecimalValueRange
import dev.brella.kornea.annotations.ExampleDecimalValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioAnalysisSegment(
    /** The starting point (in seconds) of the segment. */
    @ExampleDecimalValue(0.70154)
    val start: Double,

    /** The duration (in seconds) of the segment. */
    @ExampleDecimalValue(0.19891)
    val duration: Double,

    /** The confidence, from 0.0 to 1.0, of the reliability of the segmentation.
     *
     *  Segments of the song which are difficult to logically segment (e.g: noise) may correspond to low values in this field. */
    @ExampleDecimalValue(0.435)
    @DecimalValueRange(0.0, 1.0)
    val confidence: Double,

    /** The onset loudness of the segment in decibels (dB).
     *
     * Combined with loudness_max and loudness_max_time, these components can be used to describe the "attack" of the segment. */
    @SerialName("loudness_start")
    @ExampleDecimalValue(-23.053)
    val loudnessStart: Double,

    /** The peak loudness of the segment in decibels (dB).
     *
     *  Combined with loudness_start and loudness_max_time, these components can be used to describe the "attack" of the segment. */
    @SerialName("loudness_max")
    @ExampleDecimalValue(-14.25)
    val loudnessMax: Double,

    /** The segment-relative offset of the segment peak loudness in seconds.
     *
     *  Combined with loudness_start and loudness_max, these components can be used to desctibe the "attack" of the segment. */
    @SerialName("loudness_max_time")
    @ExampleDecimalValue(0.07305)
    val loudnessMaxTime: Double,

    /** The offset loudness of the segment in decibels (dB).
     *
     *  This value should be equivalent to the loudness_start of the following segment. */
    @SerialName("loudness_end")
    @ExampleDecimalValue(0.0)
    val loudnessEnd: Double,

    /** Pitch content is given by a “chroma” vector, corresponding to the 12 pitch classes C, C#, D to B, with values ranging from 0 to 1 that describe the relative dominance of every pitch in the chromatic scale.
     *
     *  For example a C Major chord would likely be represented by large values of C, E and G (i.e. classes 0, 4, and 7).
     *
     *  Vectors are normalized to 1 by their strongest dimension, therefore noisy sounds are likely represented by values that are all close to 1, while pure tones are described by one value at 1 (the pitch) and others near 0.
     *  As can be seen below, the 12 vector indices are a combination of low-power spectrum values at their respective pitch frequencies.
     *
     *  [log power-spectrum](https://developer.spotify.com/assets/audio/Pitch_vector.png) */
    @DecimalValueRange(0.0, 1.0)
    val pitches: DoubleArray,

    /** Timbre is the quality of a musical note or sound that distinguishes different types of musical instruments, or voices.
     *
     *  It is a complex notion also referred to as sound color, texture, or tone quality, and is derived from the shape of a segment’s spectro-temporal surface, independently of pitch and loudness.
     *  The timbre feature is a vector that includes 12 unbounded values roughly centered around 0.
     *  Those values are high level abstractions of the spectral surface, ordered by degree of importance.
     *
     *  For completeness however, the first dimension represents the average loudness of the segment; second emphasizes brightness; third is more closely correlated to the flatness of a sound; fourth to sounds with a stronger attack; etc.
     *  See an image below representing the 12 basis functions (i.e. template segments).
     *
     *  [12 basis functions for the timbre vector](https://developer.spotify.com/assets/audio/Timbre_basis_functions.png)
     *
     *  The actual timbre of the segment is best described as a linear combination of these 12 basis functions weighted by the coefficient values: timbre = c1 x b1 + c2 x b2 + ... + c12 x b12, where c1 to c12 represent the 12 coefficients and b1 to b12 the 12 basis functions as displayed below.
     *  Timbre vectors are best used in comparison with each other.
     */
    val timbre: DoubleArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpotifyTrackAudioAnalysisSegment

        if (start != other.start) return false
        if (duration != other.duration) return false
        if (confidence != other.confidence) return false
        if (loudnessStart != other.loudnessStart) return false
        if (loudnessMax != other.loudnessMax) return false
        if (loudnessMaxTime != other.loudnessMaxTime) return false
        if (loudnessEnd != other.loudnessEnd) return false
        if (!pitches.contentEquals(other.pitches)) return false
        return timbre.contentEquals(other.timbre)
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + confidence.hashCode()
        result = 31 * result + loudnessStart.hashCode()
        result = 31 * result + loudnessMax.hashCode()
        result = 31 * result + loudnessMaxTime.hashCode()
        result = 31 * result + loudnessEnd.hashCode()
        result = 31 * result + pitches.contentHashCode()
        result = 31 * result + timbre.contentHashCode()
        return result
    }
}
