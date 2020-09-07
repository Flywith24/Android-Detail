object GradlePlugins {
    var KotlinVersion = "1.4.0"
    const val ANDROID = "com.android.tools.build:gradle:4.0.1"
    val KotlinStdlib
        get() = "org.jetbrains.kotlin:kotlin-stdlib:$KotlinVersion"

    interface GradlePlugin {
        val ID: String
        val VERSION: String
        val APPLY: Boolean
            get() = true
    }

    object Kotlin : GradlePlugin {
        override val ID = "gradle-plugin"
        override val VERSION = "1.4.0"
    }
}
