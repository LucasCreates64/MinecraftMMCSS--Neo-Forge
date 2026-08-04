import org.gradle.internal.fingerprint.classpath.impl.ClasspathFingerprintingStrategy.runtimeClasspath

plugins {
    alias(libs.plugins.fabric.loom)
}

version = "1.0.0"

repositories {
    mavenCentral()
}

configurations {
    runtimeClasspath {
        extendsFrom(include)
    }
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    
    include(libs.jna)
    include(libs.jna.platform)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

tasks.withType<Jar>().configureEach {
    archiveBaseName = "WindowsPriorityBoost"
}
