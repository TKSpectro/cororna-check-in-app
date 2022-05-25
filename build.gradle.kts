// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "7.2.0-rc02" apply false
    id("com.android.library") version "7.2.0-rc02" apply false
    id("org.jetbrains.kotlin.android") version Libs.Kotlin.version apply false

    id(Plugins.detekt).version(Versions.detekt)
    id(Plugins.dokka).version (Versions.dokka)
}

subprojects {
    apply {
        plugin(Plugins.detekt)
        plugin(Plugins.dokka)
    }

    detekt {
        toolVersion = Versions.detekt
        config = files("../config/detekt/detekt.yml")
        buildUponDefaultConfig = true
    }
}

dependencies {
    detektPlugins( Plugins.detekt_plugin_formatting )
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokkaMultiModuleOutput"))
}