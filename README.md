# Star Wars app

An Android application that displays information from the Star Wars universe, including characters, etc.

## Module Structure

To follow **Clean Architecture** principles, this application is organized into five modules: `app`, `ui`, `presentation`, `domain`, and `data`.

### `app` Module

The main Android module that contains the `Application` class and the `AndroidManifest.xml` file, which defines the launcher activity.
It has dependencies on the following modules:
* `presentation`: To access the launcher Activity defined in the manifest.
* `data`: So the Hilt modules in `data` can properly inject use cases (and their repositories) into the view models.

> **Note:** Hilt is not used in the `domain` module because it is a pure Kotlin module, not an Android module, and cannot use the `@Inject` or `@Module` annotations.


### `ui` Module

An Android module that includes UI resources like colors, themes, and styles. It has no dependencies on other modules.


### `presentation` Module

An Android module that contains the Activities, Screens, and ViewModels.
It has dependencies on the following modules:
* `ui`: To use the themes, colors, and other UI resources.
* `domain`: To call the use cases defined in the `domain` module.


### `domain` Module

A Kotlin module that includes use cases and repository interfaces. It has no dependencies on other modules.


### `data` Module

An Android module that includes the implementation of the repository interfaces defined in the `domain` module, as well as local and remote data sources.
It depends on the `domain` module to access the repository interfaces it must implement.


## Libraries

Hilt: For dependency inyection.
Retrofit: To handle REST API calls.

Truth: For unit testing assertions
Mockk: For unit testing mocks
