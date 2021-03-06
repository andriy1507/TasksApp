plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
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
            buildConfigField("Boolean","RELEASE", "true")
            buildConfigField(
                "String",
                "SERVER_API_URL",
                "\"${variables.serverApiUrl}\""
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean","RELEASE", "false")
            buildConfigField(
                "String",
                "SERVER_API_URL",
                "\"${variables.serverApiUrl}\""
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
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.core)
    compileOnly(libs.androidx.annotations)
    implementation(libs.coroutines.core)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.extensions)
    implementation(libs.picasso.core)
    testImplementation(libs.testing.junit)
    testImplementation(libs.coroutines.testing)
}