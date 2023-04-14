package dev.brella.ktornea.spotify.data.tracks.analysis

import dev.brella.ktornea.spotify.data.StringSemVer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyTrackAudioAnalysisMeta(
    /** The version of the Analyzer used to analyze this track. */
    @SerialName("analyzer_version")
    val analyserVersion: StringSemVer,

    /** The platform used to read the track's audio data. */
    val platform: String,

    /** A detailed status code for this track.
     *  If analysis data is missing, this code may explain why. */
    @SerialName("detailed_status")
    val detailedStatus: String,

    /** The return code of the analyzer process.
     *  0 if successful, 1 if any errors occurred. */
    @SerialName("status_code")
    val statusCode: Int,

    /** The Unix timestamp (in seconds) at which this track was analyzed. */
    val timestamp: Long,

    /** The amount of time taken to analyze this track. */
    @SerialName("analysis_time")
    val analysisTime: Double,

    /** The method used to read the track's audio data. */
    @SerialName("input_process")
    val inputProcess: String,
)
