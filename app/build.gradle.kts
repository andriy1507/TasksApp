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
    composeOptions {
        kotlinCompilerVersion = libs.kotlin.version
        kotlinCompilerExtensionVersion = libs.androidx.compose.version
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(project(":core"))
    implementation(project(":core-utils"))
    implementation(project(":core-ui"))
    implementation(project(":repository"))
    implementation(project(":remote"))
    implementation(project(":local"))
    implementation(project(":splash"))
    implementation(project(":main"))
    implementation(project(":create"))
    implementation(project(":profile"))
    implementation(project(":settings"))
    implementation(project(":account"))
    implementation(project(":firebase"))
    implementation(project(":location"))
    implementation(project(":exoplayer"))
    implementation(project(":maps"))
    implementation(libs.google.auth)
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.multidex)
    implementation(libs.hilt.core)
    implementation(libs.hilt.viewModel)
    kapt(libs.hilt.androidCompiler)
    kapt(libs.hilt.compiler)
    compileOnly(libs.androidx.annotations)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.google.material.design)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.extensions)
    implementation(libs.stetho.core)
    implementation(libs.logging.timber)
    implementation(libs.google.firebase.analytics)
    implementation(libs.google.firebase.crashlytics)
    implementation(libs.picasso.core)
    implementation(libs.retrofit.core)
    implementation(libs.androidx.room.core)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.stetho.okhttp)
    implementation(libs.google.gson)
    implementation(libs.tinder.scarlet)
    implementation(libs.groupie.core)
    implementation(libs.androidx.paging.core)
    implementation(libs.google.firebase.messaging)
}