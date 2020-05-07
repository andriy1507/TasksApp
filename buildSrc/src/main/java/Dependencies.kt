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

    object room {
        private const val room_version = "2.2.5"
        const val core = "androidx.room:room-runtime:$room_version"
        const val compiler = "androidx.room:room-compiler:$room_version"
        const val extensions = "androidx.room:room-ktx:$room_version"
    }

    object paging {
        private const val paging_version = "2.1.2"
        const val core = "androidx.paging:paging-runtime:$paging_version"
    }

    object coroutines {
        private const val coroutines_version = "1.3.5"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    }

    object constraint {
        private const val constraint_version = "2.0.0-beta4"
        const val layout = "com.android.support.constraint:constraint-layout:$constraint_version"
    }

    object material {
        private const val material_version = "1.1.0-alpha10"
        const val design = "com.google.android.material:material:$material_version"
    }

    object navigation {
        private const val navigation_version = "2.3.0-alpha06"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
        const val extensions = "androidx.navigation:navigation-ui-ktx:$navigation_version"
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