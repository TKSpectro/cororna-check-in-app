
object BuildPlugins {
    //const val android = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    //const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val androidx_espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.androidx_espresso_core}"
    const val androidx_compose_junit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    // Added for Android 12 workaround (exported = true) TODO: Remove as soon as possible
    const val androidx_test_ext_junit = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext_junit}"
}

object Dependencies {

    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
    const val androidx_compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val androidx_compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val androidx_compose_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val androidx_lifecycle_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle_ktx}"
    const val androidx_activity_compose =
        "androidx.activity:activity-compose:${Versions.androidx_activity_compose}"
}

object DebugDependencies {
    const val androidx_compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
}

object Plugins {
    const val android_app = "com.android.application"
    const val kotlin_android = "org.jetbrains.kotlin.android"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val detekt_plugin_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    const val dokka = "org.jetbrains.dokka"
}

