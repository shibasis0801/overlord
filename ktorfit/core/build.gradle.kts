plugins {
    kotlin("multiplatform") version "1.4.10"
    kotlin("kapt") version "1.4.10"
    id("maven-publish")
}
group = "org.overlord"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js(IR) {
        browser {
            webpackTask {
                output.libraryTarget = "commonjs2"
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    sourceSets {
        val build by creating {
            kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
        }

        val commonMain by getting {
            dependsOn(build)
            dependencies {
                api(project(":base"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.squareup.okhttp3:okhttp:4.9.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}


tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile::class.java).named("compileKotlinJs") {
    kotlinOptions.moduleKind = "commonjs"
}

dependencies {
    "kapt"(project(":processor"))
}
