// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp.plugin) apply false

    id("jacoco")
}

tasks.register<JacocoReport>("jacocoRootReport") {
    group = "verification"
    description = "Genera reporte JaCoCo combinado de todos los módulos"

    // 1- Dependencies: First run all the local test tasks
    dependsOn(
        ":presentation:jacocoPresentationTestReport",
        ":data:jacocoDataTestReport",
        ":domain:jacocoDomainTestReport"
    )

    // 2- Get all the .exec files from all modules
    executionData.setFrom(
        files(
            "presentation/build/jacoco/testDebugUnitTest.exec",
            "data/build/jacoco/testDebugUnitTest.exec",
            "domain/build/jacoco/test.exec"
        )
    )

    // 3- Directories with the compiled classes (excluding auto-generated files)
    classDirectories.setFrom(
        files(
            fileTree("presentation/build/tmp/kotlin-classes/debug") {
                exclude(
                    "**/R.class",
                    "**/R$*.class",
                    "**/BuildConfig.*",
                    "**/*Test*.*"
                )
            },
            fileTree("data/build/tmp/kotlin-classes/debug") {
                exclude(
                    "**/R.class",
                    "**/R$*.class",
                    "**/BuildConfig.*",
                    "**/*Test*.*"
                )
            },
            fileTree("domain/build/classes/kotlin/main") {
                exclude("**/*Test*.*")
            }
        )
    )

    // 4- Source code directories
    sourceDirectories.setFrom(
        files(
            "presentation/src/main/kotlin",
            "data/src/main/kotlin",
            "domain/src/main/kotlin"
        )
    )

    // 5- Reports configuration
    reports {
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/jacocoRootReport/html"))
        xml.required.set(true)
        xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/jacocoRootReport/report.xml"))
    }
}
