plugins {
    id("com.flywith24.version") apply false
}

buildscript {
    val kotlinVersion by extra("1.6.0")
    val android by extra("com.android.tools.build:gradle:7.0.3")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(android)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
subprojects {
    // 子项目统一应用插件
    project.apply(plugin = "com.flywith24.version")
    if ("baselib" == project.name) project.apply(plugin = "com.android.library")
    else project.apply(plugin = "com.android.application")
}
tasks.register<Delete>("clean") {
    delete(buildDir)
}