plugins {
    alias(libs.plugins.anirumy.feature)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

val appPackageName: String by rootProject.extra

android {
    namespace = "$appPackageName.feature.gallery"
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil.compose)
}
