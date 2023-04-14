package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.*
import dev.brella.ktornea.spotify.data.types.*
import kotlinx.serialization.Serializable

@Serializable(EnumSpotifyType.Serialiser::class)
public sealed class EnumSpotifyType(override val type: String) : KorneaStringEnum {
    public object Track : EnumSpotifyType("track"), SpotifyTypeOfTrack, SpotifySeedType
    public object AudioFeatures : EnumSpotifyType("audio_features"), SpotifyAudioFeatureType


    public object Album : EnumSpotifyType("album"), SpotifyAlbumType, SpotifyTypeOfAlbum
    public object Single : EnumSpotifyType("single"), SpotifyAlbumType
    public object Compilation : EnumSpotifyType("compilation"), SpotifyAlbumType
    public object AppearsOn : EnumSpotifyType("appears_on"), SpotifyAlbumGroup

    public object Artist : EnumSpotifyType("artist"), SpotifyArtistType

    public class Unknown(type: String) : EnumSpotifyType(type),
        SpotifyTypeOfTrack, SpotifyAudioFeatureType, SpotifySeedType,
        SpotifyAlbumType, SpotifyAlbumGroup, SpotifyArtistType {
        override fun toString(): String = "Unknown(\"$type\")"
    }

    public companion object Serialiser : KorneaStringEnumSerialiser<EnumSpotifyType>(lazyMap {
        arrayOf(
            Track, AudioFeatures,

            Album, Single, Compilation, AppearsOn,

            Artist
        ).associateByTo(this, KorneaStringEnum::type)
    }, "EnumSpotifyType", EnumCaseSensitivity.LOWER_CASE, EnumCaseSensitivity.LOWER_CASE, default = ::Unknown)

    override fun toString(): String = type
}