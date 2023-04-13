package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.*
import kotlinx.serialization.Serializable

@Serializable(EnumSpotifyType.Serialiser::class)
public sealed class EnumSpotifyType(override val type: String) : KorneaStringEnum {
    public object Track : EnumSpotifyType("track")
    public object AudioFeatures : EnumSpotifyType("audio_features")

    public class Unknown(type: String) : EnumSpotifyType(type)

    public companion object Serialiser : KorneaStringEnumSerialiser<EnumSpotifyType>(lazyMap {
        arrayOf(
            Track, AudioFeatures
        ).associateByTo(this, KorneaStringEnum::type)
    }, "EnumModality", ::Unknown)
}