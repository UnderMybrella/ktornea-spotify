package dev.brella.ktornea.spotify.data.enums

import dev.brella.kornea.serialisation.core.common.*
import kotlinx.serialization.Serializable

@Serializable(EnumDatePrecision.Serialiser::class)
public sealed class EnumDatePrecision(override val type: String) : KorneaStringEnum {
    //    public object
    public object Year : EnumDatePrecision("year")
    public object Month : EnumDatePrecision("month")
    public object Day : EnumDatePrecision("day")

    public class Unknown(type: String) : EnumDatePrecision(type)

    public companion object Serialiser : KorneaStringEnumSerialiser<EnumDatePrecision>(lazyMap {
        arrayOf(Year, Month, Day)
            .associateByTo(this, KorneaStringEnum::type)
    }, "EnumDatePrecision", EnumCaseSensitivity.LOWER_CASE, EnumCaseSensitivity.LOWER_CASE, ::Unknown)
}