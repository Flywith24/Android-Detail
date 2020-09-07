import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Flywith24
 * @date   2020/8/11
 * time   9:13
 * description
 */
class VersionConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //根据 project build.gradle 中的配置动态设置 kotlinVersion
        GradlePlugins.KotlinVersion =
            project.rootProject.extensions.extraProperties.get("kotlinVersion") as String
    }
}