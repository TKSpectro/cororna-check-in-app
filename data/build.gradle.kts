plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_kapt)
}

android {
    compileSdk = Config.compile_sdk_version

    defaultConfig {
        minSdk = Config.min_sdk_version
        targetSdk = Config.target_sdk_version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.jvm_target
    }
}


dependencies {

    implementation( project(mapOf("path" to ":domain")) )
    implementation( project(mapOf("path" to ":android-core")) )
    implementation( Libs.AndroidX.coreKtx )
    implementation( Libs.Coroutines.android )

    kapt( Libs.Room.compiler )
    implementation( Libs.Room.runtime )
    implementation( Libs.Room.ktx )
    androidTestImplementation( Libs.Room.testing )

    testImplementation( TestDependencies.junit)
    androidTestImplementation( TestDependencies.androidx_test_ext_junit)
    androidTestImplementation( TestDependencies.androidx_espresso_core)
}