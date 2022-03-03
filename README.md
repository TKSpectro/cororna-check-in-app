# android-clean-arch-template

An Android application template to create a new Android project in just a couple of seconds. It's
based on Clean Architecture and uses Gradle modules to separate the different layers and aspects of
this architectural approach. 

## How to use

- Download/Clone this repo
- Update the following:
  - [application_id](buildSrc/src/main/java/Config.kt)
  - AndroidManifest ([here](app/src/main/AndroidManifest.xml) and [here](android-core/src/main/AndroidManifest.xml))
  - Packages in your source files

## Features

- 100% Kotlin
- 100% Gradle Kotlin DSL
- `buildSrc` module for general build configuration & dependency management
- Kotlin Static Analysis via `ktlint` and [`detekt`](https://github.com/detekt/detekt).
- Jetpack Compose based UI
- Database Access with Room
- Sample Unit, Instrumented and UI tests
- CI/CD with GitLab Pipelines

## Modules

### buildSrc

Special directory/module which gets picked up by Gradle before anything else is build. Thus, everything
defined in `buildSrc` will be available on successive build steps. Due to this property, this 
module as emerged as the de-facto standard to simplify the configuration of multi-module projects as
well as the central place to define dependencies and their corresponding versions.

In this template, the `buildSrc` module contains three different files:

1. `Config`
Contains general project properties like *application id*, *min sdk version* or *version name*.

2. `Dependencies`
Contains all dependency references which are used in your project. Thus, if more than one module 
needs the same dependency, they can now all reference the same version. Which makes it easy to 
update/change a dependency as one only needs to change it in this place.

3. `Versions`
Related to the file above, this one contains the versions for all dependencies. Entries are referenced
inside `Dependencies`.

Using the defined values is simple - just add them to your module-specific Gradle build files like
in the following examples:

```kotlin
// From app/build.gradle.kts 
android {
  compileSdk = Config.compile_sdk_version
  buildToolsVersion = Config.build_tools_version

  defaultConfig {
    applicationId = Config.application_id
    minSdk = Config.min_sdk_version
    // ...
  }
  // ...
}
```
      
```kotlin
// From domain/build.gradle.kts 
dependencies {
    implementation( Dependencies.kotlin_coroutines_core )
}
```

For some additional details regarding the `buildSrc` module see [Better Dependency Management Using 
buildSrc + Kotlin DSL](https://proandroiddev.com/better-dependencies-management-using-buildsrc-kotlin-dsl-eda31cdb81bf) 
as well as [Organizing Gradle Projects: Use buildSrc to abstract imperative logic](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)

### domain

Our inner layer with regard to Clean Architecture. Completely Android agnostic (as can be seen by
its dependency list which just added support for Kotlin Coroutines). Contains our domain 
model, use cases/interactors as well as interface definitions for various platform-specific details
like repositories, logging, networking or file system handling.

### android-core

Contains some general Android-specific implementations. Like an implementation for the logger 
interface defined in our domain module, which uses the nice [`Timber`](https://github.com/JakeWharton/timber) 
library.  

### data

Contains the data management code like a database model, database access code and a repository 
implementation that conforms to the interface defined in our domain module.

Thus, all database related dependencies are only present in this module which prevents the usage of 
database classes/frameworks in other modules. A nice effect of separating the different layers of 
Clean Architecture with Gradle modules - each single module only references third party frameworks
that are absolutely necessary. We don't get a single *Über* module that contains everything.

### app

Our *frontend* module that contains all UI related stuff. ViewModels use our use cases to perform 
business operations to query/alter data which afterwards will be displayed in our UI.

## Static Analysis

### detekt & klint

Run `detekt` Gradle target to run a static code analysis and generate a report that contains 
for example code smells and complexity data based on lines of code, cyclomatic complexity and amount 
of code smells. In this template, `detekt` is configured to run [`klint`](https://github.com/pinterest/ktlint) 
as part of its analysis to check formatting. 

Reports can be found in `<module>/build/reports/detekt`. 
Configuration is given in [`config/detekt/detekt.yml`](config/detekt/detekt.yml)

For more information on `detekt` see [`https://detekt.dev`](https://detekt.dev)

## Test

*tbd*