import dev.brella.kornea.gradle.*
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.8.0" apply false
    kotlin("plugin.serialization") version "1.8.0" apply false

    id("dev.brella.kornea") version "2.1.0" apply false
}

defineVersions {
    ktor("2.2.4")
    kotlinxCoroutines("1.7.0-Beta")
    kotlinxSerialisation("1.5.0")

    korneaErrors("3.1.0-alpha")
    "ktornea-client-results" .. "1.2.0-alpha"
}