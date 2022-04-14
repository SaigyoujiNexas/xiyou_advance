plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}
var versionCode : Int? = null
var versionName : String? = null
android {


        defaultConfig {
            compileSdk = androidC["compileSdk"] as Int
            minSdk = androidC["minSdk"] as Int
            targetSdk = androidC["targetSdk"] as Int
            versionCode = androidC["versionCode"] as Int
            versionName = androidC["versionName"] as String
            buildToolsVersion = androidC["buildToolsVersion"] as String
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")

            javaCompileOptions {
                annotationProcessorOptions {
                }
            }
        }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        compileOptions {
            sourceCompatibility(javaVersion)
            targetCompatibility(javaVersion)
        }
    kotlinOptions{
        jvmTarget = JvmTarget
    }
    }

    dependencies {
        libraryC.forEach{ (_, v) -> implementation(v)}
        libs.forEach { s -> implementation(s) }
        tests.forEach { t -> androidTestImplementation(t) }
        apts.forEach { s -> kapt(s) }
        libKtx.forEach { s -> implementation(s) }

        testImplementation("junit:junit:4.+")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        implementation("androidx.core:core-ktx:+")
    }

