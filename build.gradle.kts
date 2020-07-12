buildscript {
    val kotlin_version by extra("1.3.72")
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
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/lisawray/maven")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}