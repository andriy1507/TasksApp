plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdkVersion(variables.compileSdk)
    buildToolsVersion = variables.buildTools
    defaultConfig {
        applicationId = "com.spaceapps.tasks"
        minSdkVersion(28)
        targetSdkVersion(30)
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
    implementation(project(":core"))
    implementation(project(":core-utils"))
    implementation(project(":repository"))
    implementation(project(":local"))
    implementation(project(":remote"))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.wear.ui)
    implementation(libs.androidx.wear.input)
    implementation(libs.hilt.core)
    implementation(libs.hilt.viewModel)
    kapt(libs.hilt.androidCompiler)
    kapt(libs.hilt.compiler)
    compileOnly(libs.androidx.annotations)
    implementation(libs.google.firebase.analytics)
    implementation(libs.google.firebase.crashlytics)
    implementation(libs.androidx.navigation.extensions)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.groupie.core)
    implementation(libs.groupie.viewBinding)
    implementation(libs.retrofit.core)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.okhttp.core)
    implementation(libs.androidx.room.core)
    implementation(libs.picasso.core)
    implementation(libs.stetho.okhttp)
    implementation(libs.google.gson)
    implementation(libs.tinder.scarlet)
}