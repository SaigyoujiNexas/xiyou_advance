plugins {

    if(isDebug)
        id("com.android.application")
    else
        id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = androidC["compileSdk"] as Int
    val applicationId: String = applicationIds["community"]!!
    var versionName: String = androidC["versionName"] as String
    var versionCode: Int = androidC["versionCode"] as Int
    buildToolsVersion = androidC["buildToolsVersion"] as String
    defaultConfig {
        minSdk = androidC["minSdk"] as Int
        targetSdk = androidC["targetSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
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
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = JvmTarget
    }
    sourceSets["main"].manifest.srcFile{
        if(isDebug)
            "src/main/debug/AndroidManifest.xml"
        else
            "src/main/AndroidManifest.xml"
    }
}

dependencies {
    implementation(project(":modulesPublic:common"))
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    libKtx.forEach { implementation(it) }
    libs.forEach{implementation(it)}
    apts.forEach{kapt(it)}
    libraryC.forEach { (_, v) -> implementation(v)}
    tests.forEach { androidTestImplementation(it) }
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}