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
        getByName("release") {
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation( Dependencies.androidx_core_ktx)
    implementation( Dependencies.androidx_compose_ui)
    implementation( Dependencies.androidx_compose_material)
    implementation( Dependencies.androidx_compose_tooling_preview)
    implementation( Dependencies.androidx_lifecycle_ktx)
    implementation( Dependencies.androidx_activity_compose)

    testImplementation( TestDependencies.junit)
    androidTestImplementation( TestDependencies.androidx_compose_junit)
    androidTestImplementation( TestDependencies.androidx_espresso_core)
    androidTestImplementation( TestDependencies.androidx_compose_junit)
    androidTestImplementation( TestDependencies.androidx_test_ext_junit)

    debugImplementation( DebugDependencies.androidx_compose_ui_tooling)
}