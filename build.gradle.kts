buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(libs.android.tools)
        classpath(libs.kotlin.plugin)
        classpath(libs.navigation.safe_args)
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
    }
}

task("clean") {
    delete(rootProject.buildDir)
}