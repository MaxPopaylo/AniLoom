plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    //MARK: Pagination
    implementation(libs.androidx.paging.common)

    // Javax Inject
    api(libs.javax.inject)

    // Kotlin Coroutines
    api(libs.kotlinx.coroutines.core)
}
