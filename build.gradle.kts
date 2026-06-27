plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    `maven-publish`
}

group = "org.tywrapstudios"
version = "1.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.slf4j.api)

    testImplementation(libs.slf4j.simple)
    testImplementation(kotlin("test"))
}

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
//        maven {
//            name = "GitHubPackages"
//            url = uri("https://maven.pkg.github.com/Tywrap-Studios/hookt")
//            credentials {
//                username = System.getenv("GITHUB_ACTOR")
//                password = System.getenv("GITHUB_TOKEN")
//            }
//        }
        maven {
            name = "tywrapStudiosMvnReleases"
            url = uri("https://maven.tiazzz.me/releases")
            credentials {
                username = System.getenv("TS_MAVEN_USERNAME")
                password = System.getenv("TS_MAVEN_SECRET")
            }
        }
        maven {
            name = "tywrapStudiosMvnBackup"
            url = uri("https://repo.repsy.io/itstiazzz/maven")
            credentials {
                username = System.getenv("REPSY_USERNAME")
                password = System.getenv("REPSY_SECRET")
            }
        }
    }
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}