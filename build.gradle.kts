buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(libs.android.tools)
        classpath(libs.kotlin.plugin)
        classpath(libs.navigation.safe_args)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}