package dev.brella.ktornea.spotify.data.tracks.analysis

import dev.brella.ktornea.spotify.data.DoubleSemVer
import dev.brella.ktornea.spotify.data.enums.EnumModality
import dev.brella.ktornea.spotify.data.enums.EnumPitchClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioAnalysisDetails(
    /** The exact number of audio samples analyzed from this track. See also [analysisSampleRate]. */
    @SerialName("num_samples")
    val numberOfSamples: Long,

    /** Length of the track in seconds. */
    val duration: Double,

    /** This field will always contain the empty string. */
    @SerialName("sample_md5")
    val sampleMD5: String,

    /** An offset to the start of the region of the track that was analyzed.
     *  (As the entire track is analyzed, this should always be 0.) */
    @SerialName("offset_seconds")
    val offsetSeconds: Int,

    /** An offset to the start of the region of the track that was analyzed.
     *  (As the entire track is analyzed, this should always be 0.) */
    @SerialName("window_seconds")
    val windowSeconds: Int,

    /** The sample rate used to decode and analyze this track.
     *  May differ from the actual sample rate of this track available on Spotify. */
    @SerialName("analysis_sample_rate")
    val analysisSampleRate: Int,

    /** The number of channels used for analysis.
     *  If 1, all channels are summed together to mono before analysis. */
    @SerialName("analysis_channels")
    val analysisChannels: Int,

    /** The time, in seconds, at which the track's fade-in period ends.
     *  If the track has no fade-in, this will be 0.0. */
    @SerialName("end_of_fade_in")
    val endOfFadeIn: Double,

    /** The time, in seconds, at which the track's fade-out period starts.
     *  If the track has no fade-out, this should match the track's length. */
    @SerialName("start_of_fade_out")
    val startOfFadeOut: Double,

    /** The overall loudness of a track in decibels (dB).
     *  Loudness values are averaged across the entire track and are useful for comparing relative loudness of tracks.
     *  Loudness is the quality of a sound that is the primary psychological correlate of physical strength (amplitude).
     *  Values typically range between -60 and 0 db. */
    val loudness: Double,

    /** The overall estimated tempo of a track in beats per minute (BPM).
     *  In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration. */
    val tempo: Double,

    /** The confidence, from 0.0 to 1.0, of the reliability of the [tempo]. */
    @SerialName("tempo_confidence")
    val tempoConfidence: Double,

    /** An estimated time signature.
     *  The time signature (meter) is a notational convention to specify how many beats are in each bar (or measure).
     *  The time signature ranges from 3 to 7 indicating time signatures of "3/4", to "7/4". */
    @SerialName("time_signature")
    val timeSignature: Int,

    /** The confidence, from 0.0 to 1.0, of the reliability of the [time signature][timeSignature]. */
    @SerialName("time_signature_confidence")
    val timeSignatureConfidence: Double,

    /** The key the track is in.
     *  Integers map to pitches using standard [Pitch Class notation](https://en.wikipedia.org/wiki/Pitch_class).
     *  E.g. 0 = C, 1 = C♯/D♭, 2 = D, and so on.
     *  If no key was detected, the value is -1. */
    val key: EnumPitchClass,

    /** The confidence, from 0.0 to 1.0, of the reliability of the [key]. */
    @SerialName("key_confidence")
    val keyConfidence: Double,

    /** Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is derived.
     *  Major is represented by 1 and minor is 0. */
    val mode: EnumModality,

    /** The confidence, from 0.0 to 1.0, of the reliability of the [mode]. */
    @SerialName("mode_confidence")
    val modeConfidence: Double,

    /** An [Echo Nest Musical Fingerprint (ENMFP)](https://academiccommons.columbia.edu/doi/10.7916/D8Q248M4) code string for this track. */
    @SerialName("codestring")
    val codeString: String,

    /** A version number for the [Echo Nest Musical Fingerprint](https://academiccommons.columbia.edu/doi/10.7916/D8Q248M4) format used in the [code string][codeString] field. */
    @SerialName("code_version")
    val codeVersion: DoubleSemVer,

    /** An [EchoPrint](https://github.com/spotify/echoprint-codegen) code string for this track. */
    @SerialName("echoprintstring")
    val echoprintString: String,

    /** A version number for the [EchoPrint](https://github.com/spotify/echoprint-codegen) format used in the [echoprint string][echoprintString] field. */
    @SerialName("echoprint_version")
    val echoprintVersion: DoubleSemVer,

    /** A [Synchstring](https://github.com/echonest/synchdata) for this track. */
    //TODO: Implement this!!
    @SerialName("synchstring")
    val synchronisationString: String,

    /** A version number for the [Synchstring](https://github.com/echonest/synchdata) used in the [synch string][synchronisationString] field. */
    @SerialName("synch_version")
    val synchronisationVersion: DoubleSemVer,

    /** A Rhythmstring for this track. The format of this string is similar to the [Synchstring](https://github.com/echonest/synchdata).
     *  NB. There was never a *lot* of documentation or use for this from the looks of it, but the AnalyzeDocumentation is described as such:
     *  "With Analyzer v3.2 was introduce the rhythmstring, a binary representation of rhythmic impulses, or transients, over 8 frequency channels" */
    @SerialName("rhythmstring")
    val rhythmString: String,

    /** A version number for the Rhythmstring used in the [rhythm string][rhythmString] field. */
    @SerialName("rhythm_version")
    val rhythmVersion: DoubleSemVer
)
