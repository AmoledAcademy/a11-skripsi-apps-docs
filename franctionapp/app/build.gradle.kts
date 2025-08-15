plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.unidagontor.franctionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.unidagontor.franctionapp"
        minSdk = 21
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
        compose = true
    }
}

dependencies {

    dependencies {

        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation("androidx.activity:activity-compose:1.8.1")
        implementation("androidx.navigation:navigation-compose:2.9.0")

        // Compose BOM and UI Libraries
        implementation(platform("androidx.compose:compose-bom:2024.03.00"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.compose.material:material-icons-extended")

        // DataStore
        implementation("androidx.datastore:datastore-preferences:1.0.0")

        // Firebase
        implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
        implementation("com.google.firebase:firebase-firestore")
        implementation("com.google.firebase:firebase-auth")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

     
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2024.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")

        // Debug
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }



}
