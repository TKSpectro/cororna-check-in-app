plugins {
    id(Libs.Plugins.android_library)
    id(Libs.Plugins.kotlin_android)
}

android {
    compileSdk = Config.compile_sdk_version

    defaultConfig {
        minSdk = Config.min_sdk_version
        targetSdk = Config.target_sdk_version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = Config.jvm_target
    }
    namespace = "de.fhe.ai.pmc.acat.android_core"
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation( Libs.AndroidX.coreKtx )

    implementation( Libs.Timber.core )

    testImplementation( Libs.JUnit.core )
    androidTestImplementation( Libs.JUnit.ktx )
    androidTestImplementation( Libs.AndroidX.Espresso.core )
}