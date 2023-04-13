package dev.brella.ktornea.spotify.data.types

import kotlinx.serialization.SerialName

public interface SpotifyHasTempo {
    /** The overall estimated tempo of the section in beats per minute (BPM).
     *
     *  In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
     *
     *  @property ExampleValue 118.211
     *  */
    @Deprecated("")
    public val tempo: Double

    /** The confidence, from 0.0 to 1.0, of the reliability of the tempo. */
    @SerialName("tempo_confidence")
    public val tempoConfidence: Double
}