plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
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
        multiDexEnabled = true
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
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(project(":core"))
    implementation(project(":core-utils"))
    implementation(project(":core-ui"))
    implementation(project(":repository"))
    implementation(project(":splash"))
    implementation(project(":main"))
    implementation(project(":create"))
    implementation(project(":profile"))
    implementation(project(":settings"))
    implementation(project(":account"))
    implementation(project(":firebase"))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.multidex)
    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)
    compileOnly(libs.androidx.annotations)
    implementation(libs.constraint.layout)
    implementation(libs.material.design)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.extensions)
    implementation(libs.stetho.core)
    implementation(libs.logging.timber)
    implementation(libs.google.firebase.analytics)
    implementation(libs.google.firebase.crashlytics)
    implementation(libs.picasso.core)
}