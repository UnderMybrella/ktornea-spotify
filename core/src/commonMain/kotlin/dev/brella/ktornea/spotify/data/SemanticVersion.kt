package dev.brella.ktornea.spotify.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

typealias StringSemVer = @Serializable(SemanticVersion.Serialiser::class) SemanticVersion
typealias DoubleSemVer = @Serializable(SemanticVersion.DoubleSerialiser::class) SemanticVersion

/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/APACHE_2.txt file.
 */

@JvmInline
public value class SemanticVersion(private val version: Int) : Comparable<SemanticVersion> {
    public enum class ReleaseCycle(public val representation: String?) {
        RELEASE(null),
        RELEASE_CANDIDATE("rc"),
        BETA("beta"),
        ALPHA("alpha"),
        INDEV("indev");

        public companion object {
            public fun fromRepresentation(representation: String): ReleaseCycle =
                values().first { cycle -> cycle.representation?.equals(representation, true) == true }
        }
    }

    /**
     * Creates a version from [major] and [minor] components, leaving [patch] and [cycle] component zero.
     */
    public constructor(major: Int, minor: Int) : this(versionOf(major, minor, 0, null))
    public constructor(major: Int, minor: Int, patch: Int) : this(versionOf(major, minor, patch, null))
    public constructor(major: Int, minor: Int, cycle: ReleaseCycle) : this(versionOf(major, minor, 0, cycle))
    public constructor(major: Int, minor: Int, patch: Int, cycle: ReleaseCycle?) : this(
        versionOf(
            major,
            minor,
            patch,
            cycle
        )
    )

    public val major: Int get() = (version shr 16) and 0xFF
    public val minor: Int get() = (version shr 8) and 0xFF
    public val patch: Int get() = version and 0xFF
    public val cycle: ReleaseCycle get() = ReleaseCycle.values()[version shr 24 and 0xFF xor 0xFF]

    /**
     * Returns the string representation of this version
     */
    override fun toString(): String =
        buildString {
            append(major).append('.').append(minor).append('.').append(patch)
            cycle.representation?.let { rep -> append('-').append(rep) }
        }

    override fun compareTo(other: SemanticVersion): Int = version - other.version

    /**
     * Returns `true` if this version is not less than the version specified
     * with the provided [major] and [minor] components.
     */
    public fun isAtLeast(major: Int, minor: Int): Boolean = // this.version >= versionOf(major, minor, 0)
        this.version >= versionOf(major, minor)

    /**
     * Returns `true` if this version is not less than the version specified
     * with the provided [major], [minor] and [patch] components.
     */
    public fun isAtLeast(major: Int, minor: Int, patch: Int): Boolean =
        // this.version >= versionOf(major, minor, patch)
        this.version >= versionOf(major, minor, patch)

    public companion object {
        /**
         * Maximum value a version component can have, a constant value 255.
         */
        // NOTE: Must be placed before CURRENT because its initialization requires this field being initialized in JS
        public const val MAX_COMPONENT_VALUE: Int = 255

        //language=RegExp
        public const val SEMVER_REGEX: String =
            "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?\$"
        private val regex: Regex by lazy { SEMVER_REGEX.toRegex() }

        private inline fun versionOf(major: Int, minor: Int): Int {
            require(major in 0..MAX_COMPONENT_VALUE && minor in 0..MAX_COMPONENT_VALUE) {
                "Version components are out of range: $major.$minor"
            }
            return major.shl(16) or minor.shl(8)
        }

        private inline fun versionOf(major: Int, minor: Int, patch: Int): Int {
            require(major in 0..MAX_COMPONENT_VALUE && minor in 0..MAX_COMPONENT_VALUE && patch in 0..MAX_COMPONENT_VALUE) {
                "Version components are out of range: $major.$minor.$patch"
            }
            return major.shl(16) or minor.shl(8) or patch
        }

        private inline fun versionOf(major: Int, minor: Int, patch: Int, cycle: ReleaseCycle?): Int {
            require(major in 0..MAX_COMPONENT_VALUE && minor in 0..MAX_COMPONENT_VALUE && patch in 0..MAX_COMPONENT_VALUE) {
                "Version components are out of range: $major.$minor.$patch"
            }
            return ((cycle?.ordinal ?: 0) xor MAX_COMPONENT_VALUE).shl(24) or major.shl(16) or minor.shl(8) or patch
        }

        public fun fromString(string: String): SemanticVersion {
            val match = requireNotNull(regex.matchEntire(string)) {
                "Version string is not a valid semantic version: $string"
            }.groupValues

            return SemanticVersion(
                match[1].toInt(),
                match[2].toInt(),
                match[3].toInt(),
                match.getOrNull(4)
                    ?.takeIf(String::isNotBlank)
                    ?.let(ReleaseCycle.Companion::fromRepresentation)
            )
        }

        public fun fromDouble(version: Double): SemanticVersion {
            val major = floor(version).toInt()
            val version = (version - major) * 10.0
            val minor = floor(version).toInt()
            val patch = floor((version - minor) * 10.0).toInt()

            return SemanticVersion(major, minor, patch)
        }
    }

    public object Serialiser : KSerializer<SemanticVersion> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("dev.brella.ktornea.spotify.data.SemanticVersion", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: SemanticVersion) =
            encoder.encodeString(value.toString())

        override fun deserialize(decoder: Decoder): SemanticVersion =
            fromString(decoder.decodeString())
    }

    public object DoubleSerialiser : KSerializer<SemanticVersion> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("dev.brella.ktornea.spotify.data.SemanticVersionDouble", PrimitiveKind.DOUBLE)

        override fun serialize(encoder: Encoder, value: SemanticVersion) =
            encoder.encodeDouble((value.major) + (value.minor / 10.0) + (value.patch / 100.0))

        override fun deserialize(decoder: Decoder): SemanticVersion =
            fromDouble(decoder.decodeDouble())
    }
}