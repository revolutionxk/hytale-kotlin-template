plugins {
    kotlin("jvm") version "2.3.0"
    id("com.gradleup.shadow") version "9.3.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    compileOnly(files("libs/HytaleServer.jar"))
    testImplementation(kotlin("test"))
}

tasks.shadowJar {
    archiveClassifier.set("")
    relocate("kotlin", "com.example.plugin.shaded.kotlin")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.test {
    useJUnitPlatform()
}