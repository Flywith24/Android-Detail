plugins {
    id("com.flywith24.version")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

dependencies {
    "implementation"(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    "implementation"(GradlePlugins.KotlinStdlib)
    "implementation"(project(":baselib"))

    "testImplementation"(Testing.jUnit)
    "androidTestImplementation"(Testing.androidJunit)
    "androidTestImplementation"(Testing.androidRunner)
    "androidTestImplementation"(Testing.espresso)
}
