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
    androidTestImplementation(libs.bundles.androidx.test)
}
