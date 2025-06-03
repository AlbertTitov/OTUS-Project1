plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral() // Ensure this line is present to resolve Kotlin dependencies
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}