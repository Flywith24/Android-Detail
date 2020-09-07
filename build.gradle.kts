buildscript {
    val kotlinVersion by extra("1.4.0")
    val android by extra("com.android.tools.build:gradle:4.0.1")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(android)
        classpath(kotlin(module = "gradle-plugin", version = kotlinVersion))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {

    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}
