buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(libs.android.tools)
        classpath(libs.kotlin.plugin)
        classpath(libs.androidx.navigation.safe_args)
        classpath(libs.google.services)
        classpath(libs.google.firebase.crashlyticsPlugin)
        classpath(libs.hilt.plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(libs.picasso.url)
        mavenCentral()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}