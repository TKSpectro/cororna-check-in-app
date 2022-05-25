
object BuildPlugins {
    //const val android = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
}

object Libs {
    object Kotlin {
        const val version = "1.6.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.6.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        object Activity {
            private const val version = "1.4.0"
            const val activityCompose = "androidx.activity:activity-compose:$version"
        }

        object Compose {
            const val version = "1.2.0-beta02"

            const val ui = "androidx.compose.ui:ui:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiText = "androidx.compose.ui:ui-text-google-fonts:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:${version}"
            const val viewBinding = "androidx.compose.ui:ui-viewbinding:$version"
        }

        object Navigation {
            private const val version = "2.4.2"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val dynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:$version"
            const val test = "androidx.navigation:navigation-testing:$version"
            const val compose = "androidx.navigation:navigation-compose:$version"
        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

    }

    object Timber {
        // https://github.com/JakeWharton/timber
        private const val version = "5.0.1"
        const val core = "com.jakewharton.timber:timber:$version"
    }

    object Room {
        private const val version = "2.4.2"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler ="androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val testing = "androidx.room:room-testing:$version"
    }

    // https://insert-koin.io
    object Koin {
        private const val version = "3.1.6"
        const val core = "io.insert-koin:koin-core:${version}"
        const val android = "io.insert-koin:koin-android:${version}"
        const val compose = "io.insert-koin:koin-androidx-compose:${version}"
        const val navigation = "io.insert-koin:koin-androidx-navigation:${version}"
        const val test = "io.insert-koin:koin-test:${version}"
    }
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val androidx_espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.androidx_espresso_core}"

    // Added for Android 12 workaround (exported = true) TODO: Remove as soon as possible
    const val androidx_test_ext_junit = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext_junit}"
}

object Plugins {
    const val android_app = "com.android.application"
    const val android_library = "com.android.library"
    const val kotlin_android = "org.jetbrains.kotlin.android"
    const val kotlin_kapt = "kotlin-kapt"
    const val java_library = "java-library"
    const val kotlin_jvm = "org.jetbrains.kotlin.jvm"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val detekt_plugin_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    const val dokka = "org.jetbrains.dokka"
}

