plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "io.github.bcmaymonegalvao.bloquinhopy.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
