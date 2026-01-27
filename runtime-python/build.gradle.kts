plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.chaquopy)
}

android {
    namespace = "io.github.bcmaymonegalvao.bloquinhopy.runtime"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        
        python {
            buildPython("python3")
            pip {
                install("numpy")
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
