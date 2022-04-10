plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdk = androidC["compileSdk"] as Int

    defaultConfig {
        applicationId = applicationIds["app"]
        minSdk = androidC["minSdk"] as Int
        targetSdk = androidC["targetSdk"] as Int
        versionCode = androidC["versionCode"] as Int
        versionName = androidC["versionName"] as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions{
            annotationProcessorOptions {

            }
        }
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    buildToolsVersion = androidC["buildToolsVersion"] as String
}

dependencies {
    if(!isDebug) {
        implementation(project(":modulesCore:account"))
        implementation(project(":modulesCore:main"))
        implementation(project(":modulesCore:community"))
    }
    implementation(project(":modulesPublic:common"))
    libraryC.forEach { (_, s2) -> implementation(s2) }
    libs.forEach { implementation(it) }
    apts.forEach { annotationProcessor(it) }
    tests.forEach { androidTestImplementation(it) }
    libKtx.forEach { implementation(it) }
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}