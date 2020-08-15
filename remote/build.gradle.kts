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
            buildConfigField(
                "String",
                "SERVER_WS_URL",
                "\"ws://192.168.1.148:5000/web-socket/\""
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
            buildConfigField(
                "String",
                "SERVER_WS_URL",
                "\"ws://192.168.1.148:5000/web-socket/\""
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
    implementation(libs.coroutines.core)
    implementation(libs.androidx.core)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gsonConverter)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.logging.timber)
    implementation(libs.stetho.okhttp)
    implementation(libs.tinder.scarlet)
    implementation(libs.tinder.okHttpWebSocket)
    implementation(libs.tinder.coroutinesStreamAdapter)
    implementation(libs.tinder.gsonMessageAdapter)
    implementation(libs.tinder.lifecycleAndroid)
    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)
    compileOnly(libs.androidx.annotations)
}