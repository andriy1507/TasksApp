plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}
android {
    compileSdkVersion(variables.compileSdk)
    buildToolsVersion = variables.buildTools

    defaultConfig {
        applicationId = "com.spaceapps.tasks"
        minSdkVersion(variables.minSdk)
        targetSdkVersion(variables.compileSdk)
        versionCode = variables.version
        versionName = variables.versionCode

        testInstrumentationRunner = variables.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":repository"))
    implementation(project(":splash"))
    implementation(project(":main"))
    implementation(project(":create"))
    implementation(project(":profile"))
    implementation(project(":settings"))
    implementation(project(":account"))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)
    compileOnly(libs.androidx.annotations)
    implementation(libs.constraint.layout)
    implementation(libs.material.design)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.extensions)
    implementation(libs.stetho.core)
    implementation(libs.logging.timber)
}