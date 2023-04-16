package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.KorneaNumericalEnum
import dev.brella.kornea.serialisation.core.common.KorneaNumericalEnumSerialiser
import dev.brella.kornea.serialisation.core.common.lazyArray
import dev.brella.kornea.serialisation.core.common.lazyMap
import kotlinx.serialization.Serializable

@Serializable(EnumModality.Serialiser::class)
public sealed class EnumModality(override val type: Int) : KorneaNumericalEnum {
    public object NoResult : EnumModality(-1) {
        override fun toString(): String = "No Key Result"
    }
    public object Major : EnumModality(0) {
        override fun toString(): String = "Major Key"
    }
    public object Minor : EnumModality(1) {
        override fun toString(): String = "Minor Key"
    }

    public class Unknown(type: Int) : EnumModality(type) {
        override fun toString(): String = "Unknown Key ($type)"
    }

    public companion object Serialiser : KorneaNumericalEnumSerialiser.MapBased.AsByte<EnumModality>(lazyMap {
        this[-1] = NoResult
        this[0] = Major
        this[1] = Minor
    }, "EnumModality", ::Unknown)
}