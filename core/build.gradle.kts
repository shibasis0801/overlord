import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.Mode
plugins {
    kotlin("multiplatform") version "1.4.21"
    id("com.android.library")
}

group = "com.overlordcore"
version = "1.0-SNAPSHOT"

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
        binaries.executable()
    }
    android()
    sourceSets {
        /*
        ktor-client
        serialization
        coroutines
        */
        val commonMain by getting

        val webMain by getting {
            dependencies {
                implementation(devNpm("terser-webpack-plugin", "4.2.3"))
            }
        }

        val springMain by getting {

        }
        val androidMain by getting {
            dependencies {

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
