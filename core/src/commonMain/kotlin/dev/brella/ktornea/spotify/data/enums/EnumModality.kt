package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.KorneaNumericalEnum
import dev.brella.kornea.serialisation.core.common.KorneaNumericalEnumSerialiser
import dev.brella.kornea.serialisation.core.common.lazyArray
import kotlinx.serialization.Serializable

@Serializable(EnumModality.Serialiser::class)
public sealed class EnumModality(override val type: Int) : KorneaNumericalEnum {
    //    public object
    public object Major : EnumModality(0)
    public object Minor : EnumModality(1)

    public class Unknown(type: Int) : EnumModality(type)

    public companion object Serialiser : KorneaNumericalEnumSerialiser.ArrayBased.AsByte<EnumModality>(lazyArray(2) {
        this[0] = Major
        this[1] = Minor
    }, "EnumModality", ::Unknown)
}