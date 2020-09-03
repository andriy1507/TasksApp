plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdkVersion(variables.compileSdk)
    buildToolsVersion = variables.buildTools

    defaultConfig {
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
    implementation(project(":core-ui"))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.livedata)
    compileOnly(libs.androidx.annotations)
    implementation(libs.coroutines.core)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.extensions)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.hilt.core)
    implementation(libs.hilt.viewModel)
    kapt(libs.hilt.androidCompiler)
    kapt(libs.hilt.compiler)
    implementation(libs.picasso.core)
    implementation(libs.logging.timber)
}