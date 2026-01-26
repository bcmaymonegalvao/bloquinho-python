
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.chaquopy) apply false
    alias(libs.plugins.android.assetpack) apply false
}

allprojects {
    // Keep repositories centralized in settings.gradle.kts
}
