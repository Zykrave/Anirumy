plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("androidFeature") {
            id = "anirumy.android.feature"
            implementationClass = "com.zykrave.anirumy.buildlogic.AndroidFeatureConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
}
