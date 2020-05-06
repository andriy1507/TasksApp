object libs {

    object kotlin {
        const val version = "1.3.72"
        const val std = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object androidx {
        private const val core_version = "1.2.0"
        const val core = "androidx.core:core-ktx:$core_version"
        private const val appcompat_version = "1.1.0"
        const val appcompat = "androidx.appcompat:appcompat:$appcompat_version"
        private const val annotations_version = "1.1.0"
        const val annotations = "androidx.annotation:annotation:$annotations_version"

    }

    object dagger {
        private const val dagger_version = "2.27"
        const val core = "com.google.dagger:dagger:$dagger_version"
        const val compiler = "com.google.dagger:dagger-compiler:$dagger_version"
    }

}

object variables {
    const val minSdk = 24
    const val compileSdk = 29
    const val version = 1
    const val versionCode = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val buildTools = "29.0.3"
}