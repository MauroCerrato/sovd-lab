plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "com.sovdlab"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.6")
    implementation("io.ktor:ktor-server-netty:2.3.6")
    implementation("io.ktor:ktor-serialization-jackson:2.3.6")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.6")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.sovdlab.events.ApplicationKt")
}
