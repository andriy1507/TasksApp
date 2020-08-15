@Suppress("CLASSNAME")
object libs {

    object kotlin {
        private const val version = "1.3.72"
        const val std = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object androidx {
        private const val core_version = "1.2.0"
        const val core = "androidx.core:core-ktx:$core_version"
        private const val appcompat_version = "1.1.0"
        const val appcompat = "androidx.appcompat:appcompat:$appcompat_version"
        private const val annotations_version = "1.1.0"
        const val annotations = "androidx.annotation:annotation:$annotations_version"
        private const val multidex_version = "2.0.1"
        const val multidex = "androidx.multidex:multidex:$multidex_version"
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
        const val safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version"
    }

    object stetho{
        private const val stetho_version = "1.5.1"
        const val core ="com.facebook.stetho:stetho:$stetho_version"
        const val okhttp = "com.facebook.stetho:stetho-okhttp3:$stetho_version"
    }

    object squareup {
        private const val calendar_version = "1.6.5"
        const val calendar = "com.squareup:android-times-square:$calendar_version@aar"
    }

    object logging {
        private const val timber_version = "4.7.1"
        const val timber = "com.jakewharton.timber:timber:$timber_version"
    }

    object lifecycle {
        private const val lifecycle_version = "2.0.0"
        const val runtime = "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
        const val extensions = "androidx.lifecycle:lifecycle-runtime:$lifecycle_version"
    }

    object google {
        private const val services_version = "4.3.3"
        const val services = "com.google.gms:google-services:$services_version"
        private const val gson_version = "2.8.6"
        const val gson = "com.google.code.gson:gson:$gson_version"
        object firebase {
            private const val analytics_version = "17.4.3"
            const val analytics = "com.google.firebase:firebase-analytics:$analytics_version"
            private const val crashlytics_plugin_version = "2.2.0"
            const val crashlyticsPlugin =
                "com.google.firebase:firebase-crashlytics-gradle:$crashlytics_plugin_version"
            private const val crashlytics_version = "17.1.0"
            const val crashlytics = "com.google.firebase:firebase-crashlytics:$crashlytics_version"
            private const val messaging_version = "20.2.1"
            const val messaging = "com.google.firebase:firebase-messaging:$messaging_version"
        }
    }

    object android {
        private const val tools_version = "4.0.1"
        const val tools = "com.android.tools.build:gradle:$tools_version"
    }

    object groupie {
        private const val groupie_version = "2.8.0"
        const val core = "com.xwray:groupie:$groupie_version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$groupie_version"
    }

    object retrofit {
        private const val retrofit_version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$retrofit_version"
        private const val gson_converter_version = "2.9.0"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$gson_converter_version"
    }

    object okhttp {
        private const val okhttp_version = "4.8.1"
        const val core = "com.squareup.okhttp3:okhttp:$okhttp_version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    }

    object tinder {
        private const val scarlet_version = "0.1.10"
        const val scarlet = "com.tinder.scarlet:scarlet:$scarlet_version"
        const val okHttpWebSocket = "com.tinder.scarlet:websocket-okhttp:$scarlet_version"
        const val coroutinesStreamAdapter = "com.tinder.scarlet:stream-adapter-coroutines:$scarlet_version"
        const val gsonMessageAdapter = "com.tinder.scarlet:message-adapter-gson:$scarlet_version"
        const val lifecycleAndroid = "com.tinder.scarlet:lifecycle-android:$scarlet_version"
    }

    object picasso {
        private const val picasso_version = "2.71828"
        const val core = "com.squareup.picasso:picasso:$picasso_version"
    }
}

@Suppress("CLASSNAME")
object variables {
    const val minSdk = 24
    const val compileSdk = 29
    const val version = 1
    const val versionCode = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val buildTools = "29.0.3"
    const val serverApiUrl = "http://springdemo-env.eba-ytdvgmiq.eu-west-2.elasticbeanstalk.com/"
    const val serverWsUrl = "ws://springdemo-env.eba-ytdvgmiq.eu-west-2.elasticbeanstalk.com/web-socket"
}