plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.sianiodemc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sianiodemc"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

        implementation ("com.google.firebase:firebase-auth:21.0.1")
        implementation ("com.google.firebase:firebase-database:20.0.2")
        implementation ("com.google.firebase:firebase-storage-ktx:20.0.1")
        implementation ("com.google.android.gms:play-services-fido:18.1.0")
        implementation ("com.firebaseui:firebase-ui-database:8.0.0")
        implementation ("androidx.recyclerview:recyclerview:1.2.1")
        implementation ("androidx.cardview:cardview:1.0.0")
        implementation ("de.hdodenhof:circleimageview:3.1.0")
        implementation ("com.github.bumptech.glide:glide:4.12.0")
        annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
}