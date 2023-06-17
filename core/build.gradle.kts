import dev.brella.kornea.gradle.*
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    id("dev.brella.kornea")
    id("io.kotest.multiplatform")
}

group = "dev.brella"
version = "1.0.0-alpha"

repositories {
    mavenCentral()
    mavenBrella()
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
//    js(BOTH) {
//        browser {
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
//    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                ktorModules {
                    clientModules {
                        implementation(encoding())
                        implementation(contentNegotiation())
                    }
                }

                kotlinxCoroutinesModules {
                    implementation(core())
                }

                kotlinxSerialisationModules {
                    implementation(json())
                }

                implementation(korneaErrorsModule())
                implementation(korneaAnnotationsModule())
                implementation("dev.brella:kornea-serialisation-core:2.1.0-alpha")
                implementation(versioned("dev.brella:ktornea-client-results", "ktornea-client-results")) {
                    exclude("dev.brella", "ktornea-client-core")
                }
                implementation(versioned("dev.brella:ktornea-http", "ktornea-http"))
                implementation(versioned("dev.brella:ktornea-http-serialisation", "ktornea-http-serialisation"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(versioned("io.kotest:kotest-framework-engine", "kotest"))
                implementation(versioned("io.kotest:kotest-assertions-core", "kotest"))
                implementation(versioned("io.kotest:kotest-framework-datatest", "kotest"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                ktorModules {
                    clientModules {
                        implementation(cio())
                        implementation(encoding())
                        implementation(contentNegotiation())
                        implementation(auth())
                    }

                    implementation(serialisationKotlinxJson())
                }

                kotlinxCoroutinesModules {
                    implementation(core())
                    implementation(test())
                }

                kotlinxSerialisationModules {
                    implementation(core())
                    implementation(json())
                }

                implementation(versioned("io.kotest:kotest-runner-junit5", "kotest"))
            }
        }
//        val jsMain by getting {
//            dependencies {
//                implementation("org.jetbrains.kotlin:kotlinx-atomicfu-runtime:1.8.20-RC")
//            }
//        }
//        val jsTest by getting

        val buildConstants = registerBuildConstantsTask("buildConstants") {
            gitCommitShortHash("GIT_COMMIT_SHORT_HASH")
            gitCommitHash("GIT_COMMIT_LONG_HASH")
            gitBranch("GIT_BRANCH")
            gitCommitMessage("GIT_COMMIT_MESSAGE")
            gradleVersion("GRADLE_VERSION")
            gradleGroup("GRADLE_GROUP")
            gradleName("GRADLE_NAME")
            gradleDisplayName("GRADLE_DISPLAY_NAME")
            gradleDescription("GRADLE_DESCRIPTION")
            buildTimeEpoch("BUILD_TIME_EPOCH")
            buildTimeUtcEpoch("BUILD_TIME_UTC_EPOCH")
        }

        commonMain.kotlin.srcDir(buildConstants)

        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                explicitApi()
            }
        }
    }
}