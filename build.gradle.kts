plugins {
    kotlin("multiplatform") version "1.4.21"
    id("com.android.library")
}

group = "com.overlord.core"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm("commonJVM") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js("web", IR) {
        browser {
            webpackTask {
//                Put normal webpack stuff here
                output.libraryTarget = "commonjs2"
            }
        }
        binaries.executable()
    }
    jvm("spring") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    android()
    sourceSets {
        val commonMain by getting

        val webMain by getting

        val commonJVMMain by getting

        val springMain by getting {
            dependsOn(commonJVMMain)
            dependencies {

            }
        }
        val androidMain by getting {
            dependsOn(commonJVMMain)
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
