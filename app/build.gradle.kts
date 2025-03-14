plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ua.aniloom"
    compileSdk = 35

    defaultConfig {
        applicationId = "ua.aniloom"
        minSdk = 29
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    //MARK: Networking
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.moshi)
    implementation(libs.bundles.retrofit)

    //MARK: Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.anroid)

    //MARK: Fragment View Binding Delegate
    implementation(libs.fragmentviewbindingdelegate.kt)

    //MARK: Glide (Image loading)
    implementation (libs.glide)

    //MARK: Pagination
    implementation(libs.androidx.paging.runtime)

    //MARK: Core Dependencies
    implementation(libs.androidx.core.ktx)

    //MARK: UI Dependencies
    implementation(libs.bundles.androidx.ui)

    //MARK: Lifecycle Dependencies
    implementation(libs.bundles.androidx.lifecycle)

    //MARK: Navigation Dependencies
    implementation(libs.bundles.androidx.navigation)

    //MARK: Testing Dependencies
    testImplementation(libs.junit)
    testImplementation(libs.bundles.koin.tests) //MARK: Koin
    androidTestImplementation(libs.bundles.androidx.test)
}
