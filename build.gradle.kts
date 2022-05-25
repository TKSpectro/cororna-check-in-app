// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "7.2.0-rc02" apply false
    id("com.android.library") version "7.2.0-rc02" apply false
    id("org.jetbrains.kotlin.android") version Libs.Kotlin.version apply false

    id(Libs.Detekt.core) version Libs.Detekt.version
    id(Libs.Dokka.core) version Libs.Dokka.version
}

subprojects {
    apply {
        plugin(Libs.Detekt.core)
        plugin(Libs.Dokka.core)
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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokkaMultiModuleOutput"))
}