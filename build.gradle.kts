// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Libs.Plugins.android_app) version Config.gradle_version apply false
    id(Libs.Plugins.android_library) version Config.gradle_version apply false
    id(Libs.Plugins.kotlin_android) version Libs.Kotlin.version apply false

    id(Libs.Detekt.core) version Libs.Detekt.version
    id(Libs.Dokka.core) version Libs.Dokka.version
}

subprojects {
    apply {
        plugin( Libs.Detekt.core )
        plugin( Libs.Dokka.core )
    }

    detekt {
        toolVersion = Libs.Detekt.version
        config = files("../config/detekt/detekt.yml")
        buildUponDefaultConfig = true
    }
}

dependencies {
    detektPlugins( Libs.Detekt.formatting )
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath(Libs.Kotlin.gradlePlugin)
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokkaMultiModuleOutput"))
}