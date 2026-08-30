import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.koin.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

val appPackageName: String by rootProject.extra

val versionProps = Properties().also {
    it.load(project.rootProject.file("version.properties").reader())
}

android {
    namespace = "$appPackageName.core.domain"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes.all {
        buildConfigField("String", "VERSION_NAME", "\"${versionProps.getProperty("name")}\"")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(project(":core:resources"))

    implementation(libs.apollo.normalized.cache)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.annotations)
    implementation(libs.koin.android)

    coreLibraryDesugaring(libs.desugar.jdk.libs)
}