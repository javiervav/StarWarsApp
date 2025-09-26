# Star Wars app - Clean Architecture Demo/Testing/CI

An Android application that displays information from the Star Wars universe.  
The goal of this project is to explore a layered modular architecture (presentation, domain, data), along with solid testing practices and continuous integration.

## Tech Stack

* **Kotlin Coroutines & Flow** – Asynchronous and reactive programming  
* **Hilt** – Dependency injection  
* **Retrofit** – REST API calls  
* **Truth** – Unit testing assertions  
* **Mockk** – Unit testing mocks  

## Module Structure

To follow **Clean Architecture** principles, this application is organized into five modules: `app`, `ui`, `presentation`, `domain`, and `data`.

#### `app` module

The main Android module that contains the `Application` class and the `AndroidManifest.xml` file, which defines the launcher activity.
It has dependencies on the following modules:
* `presentation`: To access the launcher Activity defined in the manifest.
* `data`: So the Hilt modules in `data` can properly inject use cases (and their repositories) into the view models.

> **Note:** Hilt is not used in the `domain` module because it is a pure Kotlin module, not an Android module, and cannot use the `@Inject` or `@Module` annotations.


#### `ui` module

An Android module that includes UI resources like colors, themes, and styles. It has no dependencies on other modules.


#### `presentation` module

An Android module that contains the Activities, Screens, and ViewModels.
It has dependencies on the following modules:
* `ui`: To use the themes, colors, and other UI resources.
* `domain`: To call the use cases defined in the `domain` module.


#### `domain` module

A Kotlin module that includes use cases and repository interfaces. It has no dependencies on other modules.


#### `data` module

An Android module that includes the implementation of the repository interfaces defined in the `domain` module, as well as local and remote data sources.
It depends on the `domain` module to access the repository interfaces it must implement.



## Presentation Layer

Implemented using the **MVI architecture pattern**, with some base classes (`BaseViewModel`, etc.) to reduce boilerplate and a dedicated `Reducer` class to handle state management.  
This approach helps prevent `ViewModel`s from becoming too large.



## Continuous Integration (CI)

This project uses the following tools:

* **JaCoCo** – Generates test coverage reports  
* **SonarQube** – Performs static code analysis, detecting bugs, vulnerabilities, and code smells  
   * Reads JaCoCo’s coverage report and verifies that the project meets the quality gate (e.g., coverage ≥ 80%)  
* **GitHub Actions** – Automates tasks on every PR targeting the `main` branch:  
   * Run unit tests for `presentation`, `domain` and `data` modules  
   * Upload the JaCoCo code coverage report  
   * Execute a SonarCloud scan  
