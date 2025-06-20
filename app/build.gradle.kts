plugins {
    kotlin("jvm")
    // Apply the Application plugin to add support for building an executable JVM application.
    application
}

dependencies {
}

application {
    // Define the Fully Qualified Name for the application main class
    // (Note that Kotlin compiles `App.kt` to a class with FQN `com.example.app.AppKt`.)
    mainClass = "com.newfarm.project1.app.AppKt"
}
