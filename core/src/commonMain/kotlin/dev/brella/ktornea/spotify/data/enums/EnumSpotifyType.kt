package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.*
import dev.brella.ktornea.spotify.data.types.SpotifyAudioFeatureType
import dev.brella.ktornea.spotify.data.types.SpotifySeedType
import dev.brella.ktornea.spotify.data.types.SpotifyTrackType
import kotlinx.serialization.Serializable

@Serializable(EnumSpotifyType.Serialiser::class)
public sealed class EnumSpotifyType(override val type: String) : KorneaStringEnum {
    public object Track : EnumSpotifyType("track"), SpotifyTrackType, SpotifySeedType
    public object AudioFeatures : EnumSpotifyType("audio_features"), SpotifyAudioFeatureType

    public class Unknown(type: String) : EnumSpotifyType(type), SpotifyTrackType, SpotifyAudioFeatureType,
        SpotifySeedType

    public companion object Serialiser : KorneaStringEnumSerialiser<EnumSpotifyType>(lazyMap {
        arrayOf(
            Track, AudioFeatures
        ).associateByTo(this, KorneaStringEnum::type)
    }, "EnumSpotifyType", EnumCaseSensitivity.LOWER_CASE, EnumCaseSensitivity.LOWER_CASE, default = ::Unknown)
}