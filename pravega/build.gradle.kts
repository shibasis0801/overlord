import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

object Versions {
    const val KOTLIN = "1.5.10"
//    const val KTOR = "1.6.0"
//    const val SERIALIZATION = "1.2.1"
//    const val COROUTINES = "1.5.0"
}

plugins {
    kotlin("multiplatform") version "1.5.10"
//    kotlin("plugin.serialization") version "1.5.10"
//    kotlin("kapt") version "1.5.10"
    id("com.android.library")
    id("maven-publish")
}

group = "shibasispatnaik"
version = "0.1.0-SNAPSHOT"


repositories {
    google()
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm("server") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js("web", IR) {
        compilations.all {
            kotlinOptions.freeCompilerArgs += listOf("-Xir-per-module")
        }
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
                report = true
                outputFileName = "shibasispatnaik.pravega.js"
//                sourceMaps = true //not supported in IR yet
            }
        }
        binaries.executable()
    }
    android("android") {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.overlord.ktorfit:ktorfit:0.1.0-SNAPSHOT")
//                KtorFit
            }
        }

        val webMain by getting {
            dependencies {

            }
        }

        val androidMain by getting {
            dependencies {
//                OkHttp with CompletableFuture
            }
        }

        val serverMain by getting {
            dependencies {

            }
        }
    }

//    Too big (1.3 MB => ~300 visits per day to site on free hosting)
//    Also use React, other libraries from CDN to keep it lean
//    Use JSDelivr after figuring out CodeSplitting (basic webpack didn't work)
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-core:${Versions.KTOR}")
//                implementation("io.ktor:ktor-client-websockets:${Versions.KTOR}")
//                implementation("io.ktor:ktor-client-serialization:${Versions.KTOR}")
//                implementation("io.ktor:ktor-client-logging:${Versions.KTOR}")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION}")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:${Versions.SERIALIZATION}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
//            }
//        }
//
//        val webMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-js:${Versions.KTOR}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.COROUTINES}")
//            }
//        }
//
//        val serverMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-okhttp:${Versions.KTOR}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.COROUTINES}")
//            }
//        }
//
//        val androidMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-okhttp:${Versions.KTOR}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")
//            }
//        }
//    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
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
