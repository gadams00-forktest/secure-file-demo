buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    java
    id("io.moderne.rewrite") version("0.21.0")
    `maven-publish`
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-parameters"))
}

publishing {
    repositories {
        maven {
            name = "artifactory"
            url = uri("https://artifactory.moderne.ninja/artifactory/greg-local")
            credentials {
                username = project.property("artifactory.username") as String
                password = project.property("artifactory.password") as String
            }
        }
    }
    publications {
        create<MavenPublication>("moderne") {
            artifact(tasks["moderneJar"])
        }
    }
}
