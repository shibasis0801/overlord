import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.Mode
plugins {
    kotlin("multiplatform") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
    kotlin("kapt") version "1.4.21"
    id("com.android.library")
}

group = "com.overlordcore"
version = "1.0-SNAPSHOT"

object Versions {
    const val KTOR = "1.5.1"
    const val SERIALIZATION = "1.0.1"
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    jvm("spring") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js("web", IR) {
        browser {
            testTask {
                enabled = false
            }

            commonWebpackConfig {
                showProgress = true
                export = true
                progressReporter = true
            }

            webpackTask {
                mode = Mode.PRODUCTION
                report = true
                sourceMaps = true
            }
        }
        nodejs {
            // Can create builds for server
        }
        binaries.executable()
    }
    android()
    sourceSets {
        /*
        ktor-client
        serialization
        coroutines
        */

        val build by creating {
            kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
        }

        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:${Versions.KTOR}")
                implementation("io.ktor:ktor-client-websockets:${Versions.KTOR}")
                implementation("io.ktor:ktor-client-serialization:${Versions.KTOR}")
                implementation("io.ktor:ktor-client-logging:${Versions.KTOR}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:${Versions.SERIALIZATION}")
                implementation(project(":../common"))

            }
        }

        val webMain by getting {
            dependencies {
                implementation(devNpm("terser-webpack-plugin", "4.2.3"))
                implementation("io.ktor:ktor-client-js:${Versions.KTOR}")
            }
        }

        val springMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${Versions.KTOR}")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${Versions.KTOR}")
            }
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}

tasks.named("build") {
    doLast {
        println("Hello from build pipeline")
        println("Please generate a File build.gradle with the aar, to easily import in android")
        println("Please modify package.json in order to remove dependencies")
        println("Please minify kotlin.js in this")
        println("Please move everything from here to a generated directory or something. Subfolders (js, android, spring)")
    }
}
