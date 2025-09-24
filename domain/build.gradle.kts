plugins {
    `java-library`
    kotlin("jvm")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.truth) // Unit testing assertions
    testImplementation(libs.mockk) // Unit testing mocks
    testImplementation(libs.kotlinx.coroutines.test) // Unit testing coroutines
}
