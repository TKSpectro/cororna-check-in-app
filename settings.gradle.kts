pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application").version("7.2.0-alpha01")
        id("com.android.library").version("7.2.0-alpha01")
        id("org.jetbrains.kotlin.android").version("1.5.31")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = ("android-clean-arch-template")

include(
    ":app"
)
