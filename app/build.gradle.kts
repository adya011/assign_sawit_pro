
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.sawitpro.weightbridge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sawitpro.weightbridge"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // UI
    implementation(AndroidLibraries.swipeRefresh)

    // Dependency Injection
    implementation(AndroidLibraries.koin)
    implementation(AndroidLibraries.koinCore)

    // Navigation
    implementation(AndroidLibraries.navigation)
    implementation(AndroidLibraries.navigationFrag)

    // Networking
    implementation(AndroidLibraries.retrofit)
    implementation(AndroidLibraries.gson)
    implementation(AndroidLibraries.retrofitCoroutineAdapter)
    implementation(AndroidLibraries.gsonConverter)
    implementation(AndroidLibraries.kotlinCoroutineCore)
    implementation(AndroidLibraries.moshiConverter)

    // Database
    kapt(AndroidLibraries.roomCompiler)
    implementation(AndroidLibraries.roomKtx)
    implementation(AndroidLibraries.roomRuntime)

    // Test
    implementation(AndroidLibraries.coroutineTest)
    implementation(AndroidLibraries.archCoreTest)
    implementation(AndroidLibraries.mockk)
    implementation(AndroidLibraries.turbine)
    implementation(AndroidLibraries.mockWebServer)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

object AndroidLibraries {
    // UI
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

    // Dependency Injection
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"

    // Navigation
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutineAdapter}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    const val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}"

    // Database
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Test
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
}

object Versions {
    const val koin = "3.4.0"
    const val retrofit = "2.9.0"
    const val gson = "2.10.1"
    const val coroutineAdapter = "0.9.2"
    const val navigation = "2.5.3"
    const val gsonConverter = "2.9.0"
    const val archCoreTest = "2.1.0"
    const val coroutines = "1.6.0"
    const val mockk = "1.12.1"
    const val turbine = "0.12.1"
    const val okHttp = "5.0.0-alpha.11"
    const val moshiConverter = "2.0.0"
    const val room = "2.4.0"
    const val swipeRefreshLayout = "1.1.0"
}