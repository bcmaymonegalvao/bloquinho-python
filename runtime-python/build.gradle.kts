
plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "io.github.bcmaymonegalvao.bloquinhopy.runtime"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
