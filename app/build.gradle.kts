plugins {
    id(Libs.Plugins.android_app)
    id(Libs.Plugins.kotlin_android)
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
        jvmTarget = Config.jvm_target
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
    namespace = "de.fhe.ai.pmc.acat.app"
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
    implementation( Libs.AndroidX.Compose.runtimeLivedata )
    implementation( Libs.AndroidX.Compose.runtime )
    implementation( Libs.AndroidX.Navigation.compose )
    implementation( Libs.AndroidX.Lifecycle.runtime )
    implementation( Libs.AndroidX.Lifecycle.viewmodel )
    implementation( Libs.AndroidX.Lifecycle.viewModelCompose )

    implementation( Libs.AndroidX.CameraX.core )
    implementation( Libs.AndroidX.CameraX.camera2 )
    implementation( Libs.AndroidX.CameraX.lifecycle )
    implementation( Libs.AndroidX.CameraX.video )
    implementation( Libs.AndroidX.CameraX.view )
    implementation( Libs.AndroidX.CameraX.extensions )
    // https://proandroiddev.com/compose-camerax-on-android-58578f37e6df
    implementation( Libs.AndroidX.Camera.lifecycle )

    // https://sasikanth.dev/qr-scanning-using-camerax/
    implementation( Libs.Zxing.core )
    implementation( Libs.Zxing.embedded )

    // https://github.com/square/retrofit
    implementation( Libs.Retrofit.retrofit )
    implementation( Libs.Retrofit.converter )

    implementation( Libs.Moshi.moshi )

    implementation( Libs.Koin.core )
    implementation( Libs.Koin.android )
    implementation( Libs.Koin.compose )
    implementation( Libs.Koin.navigation )

    implementation( Libs.Accompanist.swipeRefresh )


    testImplementation( Libs.JUnit.core )
    androidTestImplementation( Libs.AndroidX.Compose.uiTest )
    androidTestImplementation( Libs.AndroidX.Espresso.core )
    androidTestImplementation( Libs.JUnit.ktx )
    androidTestImplementation( Libs.Koin.test )

    debugImplementation( Libs.AndroidX.Compose.tooling )
}