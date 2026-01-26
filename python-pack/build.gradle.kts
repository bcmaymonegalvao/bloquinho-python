
plugins { alias(libs.plugins.android.assetpack) }

android {
    assetPack {
        packName = "python-pack"
        dynamicDelivery {
            deliveryType = "install-time"
        }
    }
}
