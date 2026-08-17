plugins {
    alias(libs.plugins.anirumy.feature)
}

val appPackageName: String by rootProject.extra

android {
    namespace = "$appPackageName.feature.worker"
}

dependencies {
    implementation(libs.androidx.work.runtime)

    implementation(libs.koin.workmanager)
}
