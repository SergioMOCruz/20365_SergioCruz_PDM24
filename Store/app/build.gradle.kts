plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.sergio.store"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sergio.store"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_19.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation("com.google.firebase:firebase-auth:21.0.9")
    implementation("com.google.firebase:firebase-firestore:24.0.3")
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("io.coil-kt:coil-compose:2.4.0")

    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    implementation(libs.activity.compose)
    implementation(libs.viewmodel.compose)
    //Hilt
    implementation(libs.hilt)
    implementation(libs.material3.android)
    implementation(libs.runtime.livedata)
    ksp(libs.hilt.compiler)
    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
}