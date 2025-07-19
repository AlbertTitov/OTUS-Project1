plugins {
    kotlin("jvm") version "2.1.21"
}

group = "com.newfarm"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}