plugins {
    kotlin("multiplatform") version "1.5.10"
    id("maven-publish")
}

group = "com.shibasispatnaik"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) {compilations.all {
        kotlinOptions.freeCompilerArgs += listOf("-Xir-per-module")
    }
        browser {
            testTask {
                enabled = true
            }

            commonWebpackConfig {
                showProgress = true
                export = true
                progressReporter = true
            }

            webpackTask {
                report = true
                outputFileName = "kclient.js"
//                sourceMaps = true //not supported in IR yet
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.squareup.okhttp3:okhttp:4.9.0")
                implementation("com.beust:klaxon:5.5")

            }
        }
        val jvmTest by getting {
            dependencies {

            }
        }
        val jsMain by getting {
            dependencies {

            }
        }
        val jsTest by getting {
            dependencies {

            }
        }
    }
}
