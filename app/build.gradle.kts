plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.example.starwarsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.starwarsapp"
        minSdk = 24
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
}

dependencies {
    /**
     * Module dependencies:
     * - presentation: Needed to access the launcher Activity this module has in the manifest.
     * - data: Needed to access the Hilt modules in data, so the use case (with repositories)
     * can be injected properly in the view models. Remember I don't have Hilt in domain module,
     * because it's not an android module and I cannot use @Inject or @Module there.
     */
    implementation(project(":presentation"))
    implementation(project(":data"))

    /**
     * Hilt dependencies:
     * Needed to use @HiltAndroidApp in the Application class.
     */
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}