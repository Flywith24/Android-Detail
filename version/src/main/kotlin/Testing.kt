import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @author Flywith24
 * @date   2020/1/14
 * time   8:39
 * description
 * test dependencies
 */
object Testing {
    private const val testImplementation = "testImplementation"
    private const val androidTestImplementation = "androidTestImplementation"

    private const val jUnit = "junit:junit:4.12"
    private const val androidJunit = "androidx.test.ext:junit:1.1.1"
    private const val androidRunner = "androidx.test:runner:1.2.0"
    private const val espresso = "androidx.test.espresso:espresso-core:3.2.0"

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidJunit)
        add(androidRunner)
        add(espresso)
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add(androidTestImplementation, dependency)
        }
    }

    val testLibraries = arrayListOf<String>().apply {
        add(jUnit)
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add(testImplementation, dependency)
        }
    }


}