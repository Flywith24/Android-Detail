import com.android.build.gradle.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

/**
 * @author Flywith24
 * @date   2020/8/11
 * time   9:13
 * description
 */
class VersionConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //根据 project build.gradle.kts 中的配置动态设置 kotlinVersion
        project.plugins.config(project)
    }

    private fun PluginContainer.config(project: Project) {
        whenPluginAdded {
            when (this) {
                //com.android.application
                is AppPlugin -> {
                    //公共插件
                    project.configCommonPlugin()
                    //公共 android 配置项
                    project.extensions.getByType<AppExtension>().applyAppCommons(project)
                    //公共依赖
                    project.configAppDependencies()
                }
                //com.android.library
                is LibraryPlugin -> {
                    //公共插件
                    project.configCommonPlugin()
                    //公共 android 配置项
                    project.extensions.getByType<LibraryExtension>().applyLibraryCommons(project)
                    //公共依赖
                    project.configLibraryDependencies()
                }
                is KotlinAndroidPluginWrapper -> {
                    project.getKotlinPluginVersion()?.also { kotlinVersion ->
                        GradlePlugins.KotlinVersion = kotlinVersion
                    }
                }
            }
        }
    }

    private fun Project.configLibraryDependencies() {
        dependencies.apply {
            add(api, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add(implementation, GradlePlugins.KotlinStdlib)
            configTestDependencies()
        }
    }

    private fun Project.configAppDependencies() {
        dependencies.apply {
            add(implementation, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add(implementation, GradlePlugins.KotlinStdlib)
            add(implementation, (project(":baselib")))
            configTestDependencies()
        }
    }

    private fun DependencyHandler.configTestDependencies() {
        add(testImplementation, Testing.jUnit)
        add(androidTestImplementation, Testing.androidJunit)
        add(androidTestImplementation, Testing.androidRunner)
        add(androidTestImplementation, Testing.espresso)
    }

    private fun Project.configCommonPlugin() {
        plugins.apply("kotlin-android")
        plugins.apply("kotlin-android-extensions")
    }

    private fun AppExtension.applyAppCommons(project: Project) {
        defaultConfig { applicationId = BuildConfig.applicationId }
        applyBaseCommons(project)

    }

    private fun LibraryExtension.applyLibraryCommons(project: Project) {
        applyBaseCommons(project)

        onVariants.withBuildType("debug") {
            androidTest {
                enabled = false
            }
        }
    }

    private fun BaseExtension.applyBaseCommons(project: Project) {
        compileSdkVersion(BuildConfig.compileSdkVersion)

        defaultConfig {
            minSdkVersion(BuildConfig.minSdkVersion)
            targetSdkVersion(BuildConfig.targetSdkVersion)
            versionCode = BuildConfig.versionCode
            versionName = BuildConfig.versionName
            testInstrumentationRunner = BuildConfig.runner
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

    }
}
