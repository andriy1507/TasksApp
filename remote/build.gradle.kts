plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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
            buildConfigField("Boolean", "RELEASE", "true")
            buildConfigField(
                "String",
                "SERVER_API_URL",
                "\"http://springdemo-env.eba-ytdvgmiq.eu-west-2.elasticbeanstalk.com/\""
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "RELEASE", "false")
            buildConfigField(
                "String",
                "SERVER_API_URL",
                "\"http://springdemo-env.eba-ytdvgmiq.eu-west-2.elasticbeanstalk.com/\""
            )
        }
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
    implementation(libs.kotlin.std)
    implementation(libs.androidx.core)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gsonConverter)
    implementation(libs.okhttp.core)
    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)
    compileOnly(libs.androidx.annotations)
}