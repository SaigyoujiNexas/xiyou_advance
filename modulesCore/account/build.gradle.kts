plugins {

    if(isDebug)
        id("com.android.application")
    else
        id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = androidC["compileSdk"] as Int
    buildToolsVersion = androidC["buildToolsVersion"] as String
    val applicationId: String = applicationIds["account"]!!
    var versionName: String = androidC["versionName"] as String
    var versionCode: Int = androidC["versionCode"] as Int
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

    libKtx.forEach { implementation(it) }
    libs.forEach{implementation(it)}
    apts.forEach{annotationProcessor(it)}
    libraryC.forEach { (_, v) -> implementation(v)}
    tests.forEach { androidTestImplementation(it) }
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}