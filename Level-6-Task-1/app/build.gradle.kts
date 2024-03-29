plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.level_6_task_1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.level_6_task_1"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
//                jvmTarget = "1.8"
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00")) // Make sure this version is up-to-date
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3") // Note that material3 is used here, which is for Material You.
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00")) // Using the platform to enforce version alignment
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation component for Compose
    val navVersion = "2.7.1" // Updated from your previous 2.5.3 version
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // LiveData and Compose interop
    val liveDataVersion = "1.3.0-rc01" // This might change, ensure to use a stable version when available
    implementation("androidx.compose.runtime:runtime-livedata:$liveDataVersion")

    // Room components for local database interaction
    val roomVersion = "2.5.2" // Update if a newer version is available
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion") // This is for Java, if you're using Kotlin, prefer kapt
    kapt("androidx.room:room-compiler:$roomVersion") // Only needed for Kotlin
    implementation("androidx.room:room-ktx:$roomVersion") // Kotlin extensions for Room

    implementation("androidx.compose.material:material:1.5.0")


}