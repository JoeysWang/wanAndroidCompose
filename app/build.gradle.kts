import com.joeys.wanAndroid.buildsrc.Libs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = Libs.Project.applicationId
        minSdk = Libs.Project.minSdk
        targetSdk = Libs.Project.targetSdk
        versionCode = Libs.Project.versionCode
        versionName = Libs.Project.versionName

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Coroutines.android)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.Lifecycle.livedata)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Navigation.composeNavigation)
    implementation(Libs.material)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.runtimeLivedata)
    implementation(Libs.AndroidX.Compose.viewBinding)
    implementation(Libs.AndroidX.Lifecycle.viewmodel)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.gsonConverter)
    implementation(Libs.Retrofit.retrofitConverterMoshi)
    implementation(Libs.Retrofit.retrofitKotlinMoshi)


    implementation(Libs.Accompanist.insets)
    implementation(Libs.Accompanist.coil)
}