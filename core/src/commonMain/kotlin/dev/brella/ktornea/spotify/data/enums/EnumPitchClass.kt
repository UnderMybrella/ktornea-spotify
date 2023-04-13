package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.KorneaNumericalEnum
import dev.brella.kornea.serialisation.core.common.KorneaNumericalEnumSerialiser
import dev.brella.kornea.serialisation.core.common.lazyArray
import kotlinx.serialization.Serializable

@Serializable(EnumPitchClass.Serialiser::class)
public sealed class EnumPitchClass(override val type: Int) : KorneaNumericalEnum {
    //    public object
    public object C : EnumPitchClass(0)
    public object CSharp : EnumPitchClass(1)
    public object D : EnumPitchClass(2)
    public object DSharp : EnumPitchClass(3)
    public object E : EnumPitchClass(4)
    public object F : EnumPitchClass(5)
    public object FSharp : EnumPitchClass(6)
    public object G : EnumPitchClass(7)
    public object GSharp : EnumPitchClass(8)
    public object A : EnumPitchClass(9)
    public object ASharp : EnumPitchClass(10)
    public object B : EnumPitchClass(11)

    public class Unknown(type: Int) : EnumPitchClass(type)

    public companion object Serialiser : KorneaNumericalEnumSerialiser.ArrayBased.AsByte<EnumPitchClass>(lazyArray(12) {
        this[0] = C
        this[1] = CSharp
        this[2] = D
        this[3] = DSharp
        this[4] = E
        this[5] = F
        this[6] = FSharp
        this[7] = G
        this[8] = GSharp
        this[9] = A
        this[10] = ASharp
        this[11] = B
    }, "EnumPitchClass", ::Unknown)
}