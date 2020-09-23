@Suppress("CLASSNAME")
object libs {

    object kotlin {
        const val version = "1.4.10"
        const val std = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object androidx {
        private const val core_version = "1.3.1"
        const val core = "androidx.core:core-ktx:$core_version"
        private const val appcompat_version = "1.1.0"
        const val appcompat = "androidx.appcompat:appcompat:$appcompat_version"
        private const val annotations_version = "1.1.0"
        const val annotations = "androidx.annotation:annotation:$annotations_version"
        private const val multidex_version = "2.0.1"
        const val multidex = "androidx.multidex:multidex:$multidex_version"
        private const val insetter_version = "0.3.0"
        const val insetter = "dev.chrisbanes:insetter-ktx:$insetter_version"
        private const val swipe_refresh_version = "1.1.0"
        const val swipeRefresh =
            "androidx.swiperefreshlayout:swiperefreshlayout:$swipe_refresh_version"

        object lifecycle {
            private const val lifecycle_version = "2.2.0"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime:$lifecycle_version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
        }

        object room {
            private const val room_version = "2.3.0-alpha02"
            const val core = "androidx.room:room-runtime:$room_version"
            const val compiler = "androidx.room:room-compiler:$room_version"
            const val extensions = "androidx.room:room-ktx:$room_version"
            const val testing = "androidx.room:room-testing:$room_version"
        }

        object paging {
            private const val paging_version = "3.0.0-alpha06"
            const val core = "androidx.paging:paging-runtime:$paging_version"
        }

        object navigation {
            private const val navigation_version = "2.3.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
            const val extensions = "androidx.navigation:navigation-ui-ktx:$navigation_version"
            const val safe_args =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version"
        }

        object compose {
            const val version = "1.0.0-alpha01"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val tooling = "androidx.ui:ui-tooling:$version"
            const val runtime = "androidx.compose.runtime:runtime:1.0.0-alpha01"
            const val livedata = "androidx.compose.runtime:runtime-livedata:1.0.0-alpha01"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val text = "androidx.compose.foundation:foundation-text:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
        }

        object constraint {
            private const val constraint_version = "2.0.1"
            const val layout = "androidx.constraintlayout:constraintlayout:$constraint_version"
        }
        object workManager {
            private const val work_manager_version = "2.5.0-alpha01"
            const val core = "androidx.work:work-runtime:$work_manager_version"
            const val ktx = "androidx.work:work-runtime-ktx:$work_manager_version"
            const val testing = "androidx.work:work-testing:$work_manager_version"
        }

        object wear {
            private const val ui_version = "1.1.0-rc03"
            const val ui = "androidx.wear:wear:$ui_version"
            private const val input_version = "1.0.0-alpha01"
            const val input = "androidx.wear:wear-input:$input_version"
        }
    }

    object coroutines {
        private const val coroutines_version = "1.3.9"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val testing = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version"
    }

    object stetho {
        private const val stetho_version = "1.5.1"
        const val core = "com.facebook.stetho:stetho:$stetho_version"
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

    object google {
        private const val services_version = "4.3.3"
        const val services = "com.google.gms:google-services:$services_version"
        private const val auth_version = "18.1.0"
        const val auth = "com.google.android.gms:play-services-auth:$auth_version"
        private const val gson_version = "2.8.6"
        const val gson = "com.google.code.gson:gson:$gson_version"
        private const val location_version = "17.0.0"
        const val location = "com.google.android.gms:play-services-location:$location_version"
        private const val maps_version = "17.0.0"
        const val maps = "com.google.android.gms:play-services-maps:$maps_version"

        object material {
            private const val material_version = "1.1.0-alpha10"
            const val design = "com.google.android.material:material:$material_version"
        }

        object exoplayer {
            private const val exoplayer_version = "2.11.8"
            const val core = "com.google.android.exoplayer:exoplayer:$exoplayer_version"
        }

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
        private const val testing_version = "2.3.0"
        const val testing = "com.squareup.retrofit2:retrofit-mock:$testing_version"
    }

    object okhttp {
        private const val okhttp_version = "4.8.1"
        const val core = "com.squareup.okhttp3:okhttp:$okhttp_version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
        const val testing = "com.squareup.okhttp3:mockwebserver:$okhttp_version"
    }

    object tinder {
        private const val scarlet_version = "0.1.10"
        const val scarlet = "com.tinder.scarlet:scarlet:$scarlet_version"
        const val okHttpWebSocket = "com.tinder.scarlet:websocket-okhttp:$scarlet_version"
        const val coroutinesStreamAdapter =
            "com.tinder.scarlet:stream-adapter-coroutines:$scarlet_version"
        const val gsonMessageAdapter = "com.tinder.scarlet:message-adapter-gson:$scarlet_version"
        const val lifecycleAndroid = "com.tinder.scarlet:lifecycle-android:$scarlet_version"
    }

    object picasso {
        private const val picasso_version = "2.71828"
        const val core = "com.squareup.picasso:picasso:$picasso_version"
        const val url = "https://dl.bintray.com/lisawray/maven"
    }

    object hilt {
        private const val hilt_version = "2.28-alpha"
        private const val hilt_viewmodel_version = "1.0.0-alpha02"
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
        const val core = "com.google.dagger:hilt-android:$hilt_version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$hilt_version"
        private const val compiler_version = "1.0.0-alpha02"
        const val compiler = "androidx.hilt:hilt-compiler:$compiler_version"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hilt_viewmodel_version"
        const val workManager = "androidx.hilt:hilt-work:$hilt_viewmodel_version"
        const val testing = "com.google.dagger:hilt-android-testing:$hilt_version"
    }

    object testing {
        private const val junit_version = "4.12"
        const val junit = "junit:junit:$junit_version"
        private const val androidx_version = "1.3.0"
        const val androidx = "androidx.test:core:$androidx_version"
        const val androidRunner = "androidx.test:runner:$androidx_version"
        private const val mockito_version = "1.10.19"
        const val mockito = "org.mockito:mockito-core:$mockito_version"
        private const val espresso_version = "3.3.0"
        const val espresso = "androidx.test.espresso:espresso-core:$espresso_version"
        private const val robolectric_version = "4.4"
        const val robolectric = "org.robolectric:robolectric:$robolectric_version"
        private const val android_junit_version = "1.1.2"
        const val androidJunit = "androidx.test.ext:junit:$android_junit_version"
    }
}

@Suppress("CLASSNAME")
object variables {
    const val minSdk = 24
    const val compileSdk = 30
    const val version = 1
    const val versionCode = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val buildTools = "30.0.0"
    const val serverApiUrl = "http://springdemo-env.eba-ytdvgmiq.eu-west-2.elasticbeanstalk.com/"
    const val serverWsUrl =
        "ws://springdemo-env.eba-ytdvgmiq.eu-west-2.elasticbeanstalk.com/web-socket"
}