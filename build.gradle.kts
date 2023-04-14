import dev.brella.kornea.gradle.*
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.8.0" apply false
    kotlin("plugin.serialization") version "1.8.0" apply false

    id("dev.brella.kornea") version "2.1.0" apply false
    id("io.kotest.multiplatform") version "5.5.5" apply false
}

defineVersions {
    ktor("2.2.4")
    kotlinxCoroutines("1.7.0-Beta")
    kotlinxSerialisation("1.5.0")

    korneaErrors("4.0.0-alpha")
    korneaAnnotations("1.4.0-alpha")
    "ktornea-client-results" .. "2.0.0-alpha"
    "ktornea-http" .. "1.1.0-alpha"
    "ktornea-http-serialisation" .. "1.0.1-alpha"
    "kotest" .. "5.5.5"
}