plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.chaquopy)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "io.github.bcmaymonegalvao.bloquinhopy"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.github.bcmaymonegalvao.bloquinhopy"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
        ndk { abiFilters += listOf("arm64-v8a") }
    }

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_PATH") ?: "keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
            keyAlias = System.getenv("KEY_ALIAS") ?: ""
            keyPassword = System.getenv("KEY_PASSWORD") ?: ""
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    buildFeatures {
        compose = true
    }

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
    implementation(libs.androidx.compose.ui.graphics)
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
