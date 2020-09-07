plugins {
    id("com.flywith24.version")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

dependencies {
    "implementation"(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    val kotlinVersion: String by rootProject.extra
    println("yyz22 ${GradlePlugins.KotlinStdlib}")
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
    "implementation"(project(":baselib"))

    "testImplementation"(Testing.jUnit)
    "androidTestImplementation"(Testing.androidJunit)
    "androidTestImplementation"(Testing.androidRunner)
    "androidTestImplementation"(Testing.espresso)
}
