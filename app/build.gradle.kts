plugins {
    id(Plugins.android_app)
    id(Plugins.kotlin_android)
}

android {
    compileSdk = Config.compile_sdk_version
    buildToolsVersion = Config.build_tools_version

    defaultConfig {
        applicationId = Config.application_id
        minSdk = Config.min_sdk_version
        targetSdk = Config.target_sdk_version
        versionCode = Config.version_code
        versionName = Config.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.jvm_target
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":android-core")))
    implementation(project(mapOf("path" to ":data")))

    implementation( Libs.Coroutines.core )
    implementation( Libs.Coroutines.android )

    implementation( Libs.AndroidX.coreKtx)
    implementation( Libs.AndroidX.Activity.activityCompose )
    implementation( Libs.AndroidX.Compose.ui )
    implementation( Libs.AndroidX.Compose.material )
    implementation( Libs.AndroidX.Compose.toolingPreview )
    implementation( Libs.AndroidX.Navigation.compose )
    implementation( Libs.AndroidX.Lifecycle.runtime )
    implementation( Libs.AndroidX.Lifecycle.viewmodel )
    implementation( Libs.AndroidX.Lifecycle.viewModelCompose )

    implementation( Libs.Koin.core )
    implementation( Libs.Koin.android )
    implementation( Libs.Koin.compose )
    implementation( Libs.Koin.navigation )

    testImplementation( TestDependencies.junit )
    androidTestImplementation( Libs.AndroidX.Compose.uiTest )
    androidTestImplementation( TestDependencies.androidx_espresso_core )
    androidTestImplementation( TestDependencies.androidx_test_ext_junit )
    androidTestImplementation( Libs.Koin.test )

    debugImplementation( Libs.AndroidX.Compose.tooling )
}