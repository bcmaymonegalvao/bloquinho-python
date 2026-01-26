
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.hilt)
    alias(libs.plugins.chaquopy)

    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "io.github.bcmaymonegalvao.bloquinhopy"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.bcmaymonegalvao.bloquinhopy"
        minSdk = 26
        targetSdk = 34

        versionCode = 1
        versionName = "0.1.0"

        ndk { abiFilters += listOf("arm64-v8a") }
    }

    buildFeatures { compose = true }

    assetPacks += listOf(":python-pack")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature-notebooks"))
    implementation(project(":feature-projects"))
    implementation(project(":feature-github"))
    implementation(project(":runtime-python"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material3)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}

chaquopy {
    defaultConfig {
        python {
            pip {
                install("numpy")
                install("pandas")
                install("matplotlib")
                install("streamlit")
            }
        }
    }
}
