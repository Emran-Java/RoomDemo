plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //kotlin("kapt")
    // build.gradle.kts (Kotlin):
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.bo.roomdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bo.roomdemo"
        minSdk = 24
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

//    implementation("androidx.room:room-runtime:2.x.x")
//    kapt ("androidx.room:room-compiler:2.x.x")

    implementation("androidx.room:room-runtime:2.5.2") // or latest version
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    // optional- Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.5.2")

    //implementation("androidx.room:room-runtime:2.5.2") // Or latest version
    kapt("androidx.room:room-compiler:2.5.2")
    // optional - Kotlin Extensions and Coroutines support for Room
   // implementation("androidx.room:room-ktx:2.5.2")

}