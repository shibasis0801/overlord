import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val coroutines_version = "1.4.2"
plugins {
    application
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.10"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
group = "com.overlord"
version = "0.0.1"

application {
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
    mainClass.set("io.ktor.server.netty.EngineMain")
}


repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-locations:$ktor_version")
    implementation("io.ktor:ktor-network-tls:$ktor_version")
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutines_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("com.github.jasync-sql:jasync-postgresql:1.1.6")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}
