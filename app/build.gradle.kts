import com.sawitpro.buildsrc.AndroidLibraries

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

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