plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}
android {
    compileSdkVersion(variables.compileSdk)
    buildToolsVersion = variables.buildTools
    defaultConfig {
        applicationId = "com.spaceapps.tasks.app_wear"
        minSdkVersion(variables.minSdk)
        targetSdkVersion(variables.compileSdk)
        versionCode = variables.version
        versionName = variables.versionCode
        multiDexEnabled = true
        testInstrumentationRunner = variables.testRunner
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "tasks"
            keyPassword = "15ab0c7de1f9gh98"
            storePassword = "15ab0c7de1f9gh98"
            storeFile = file("debug-keystore.jks")
        }
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
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.extensions)
}