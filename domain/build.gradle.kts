plugins {
    `java-library`
    kotlin("jvm")
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.13"
}

tasks.register<JacocoReport>("jacocoDomainTestReport") {
    dependsOn("test") // Dependency: First execute unit tests

    group = "verification"
    description = "Generates JaCoCo report for the unit tests of the Domain module"

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val kotlinClasses = fileTree(layout.buildDirectory.dir("classes/kotlin/main").get().asFile) {
        exclude("**/*Test*.*")
    }

    classDirectories.setFrom(files(kotlinClasses))
    sourceDirectories.setFrom(files("src/main/kotlin"))
    executionData.setFrom(fileTree(layout.buildDirectory.asFile).include("jacoco/test.exec"))
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
