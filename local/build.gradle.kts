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
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
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
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}
dependencies {
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.core)
    kapt(libs.hilt.androidCompiler)
    implementation(libs.androidx.room.core)
    implementation(libs.androidx.room.extensions)
    kapt(libs.androidx.room.compiler)
    compileOnly(libs.androidx.annotations)
    implementation(libs.androidx.paging.core)
    testImplementation(libs.androidx.room.testing)
    testImplementation(libs.testing.androidx)
    testImplementation(libs.testing.androidRunner)
    testImplementation(libs.testing.junit)
    testImplementation(libs.testing.androidJunit)
    testImplementation(libs.testing.robolectric)
    testImplementation(libs.coroutines.testing)
}