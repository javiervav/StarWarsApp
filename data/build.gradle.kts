plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.serialization) // Needed to use the Json serialization in Retrofit
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.13"
}

tasks.register<JacocoReport>("jacocoDataTestReport") {

    dependsOn("testDebugUnitTest") // Dependency: First execute unit tests

    group = "verification"
    description = "Generates JaCoCo report for the unit tests of the Data module"

    reports {
        xml.required.set(true)    // Needed for CI/SonarCloud integrations
        html.required.set(true)   // Required for local visualization
        csv.required.set(false)
    }

    // Exclude auto-generated files by the Android build process
    val excludes = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*"
    )

    // Directories with the compiled classes
    val kotlinClasses = fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/debug").get().asFile) {
        exclude(excludes)
    }

    classDirectories.setFrom(files(kotlinClasses))
    sourceDirectories.setFrom(files("src/main/kotlin"))
    executionData.setFrom(
        fileTree(layout.buildDirectory.asFile).include(
            "jacoco/testDebugUnitTest.exec"
        )
    )
}

android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":domain"))

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization.converter)

    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.android.testing)
    testImplementation(libs.truth) // Unit testing assertions
    testImplementation(libs.mockk) // Unit testing mocks
    testImplementation(libs.kotlinx.coroutines.test) // Unit testing coroutines
}