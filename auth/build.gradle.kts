import dev.brella.kornea.gradle.*
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    id("dev.brella.kornea")
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
                        implementation(auth())
                    }
                }

                implementation(korneaErrorsModule())
                implementation("dev.brella:kornea-serialisation-core:2.0.0-alpha")
                implementation("dev.brella:ktornea-client-results:1.2.0-alpha")
                implementation(project(":ktornea-spotify-core"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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
                }

                kotlinxSerialisationModules {
                    implementation(core())
                    implementation(json())
                }
            }
        }
//        val jsMain by getting {
//            dependencies {
//                implementation("org.jetbrains.kotlin:kotlinx-atomicfu-runtime:1.8.20-RC")
//            }
//        }
//        val jsTest by getting

        val buildConstants = registerBuildConstantsTask("buildConstants") {
            outputDirectory.set(File(project.buildDir, "generated/kornea"))

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
    }
}