plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    `maven-publish`
}

group = "org.tywrapstudios"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.ktor)

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}