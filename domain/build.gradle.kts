plugins {
    id(Plugins.java_library)
    id(Plugins.kotlin_jvm)
}

dependencies {
    implementation( Libs.Coroutines.core )
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}