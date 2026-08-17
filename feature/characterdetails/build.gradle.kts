plugins {
    alias(libs.plugins.anirumy.feature)
}

val appPackageName: String by rootProject.extra

android {
    namespace = "$appPackageName.feature.characterdetails"
}

dependencies {
    implementation(project(":feature:editmedia"))
}
