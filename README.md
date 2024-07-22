# Kotlin Multiplatform Sample Project

This is a Kotlin Multiplatform sample project targeting Android, iOS platforms. The project demonstrates how to retrieve data from the SpaceX API, save it in a local database, and display it in a list. The libraries used include Ktor for networking, Koin for dependency injection, Room for database, and Datastore for settings. The project shares a ViewModel across different platforms with different UI implementations for each platform.

## Project Structure

* **`/composeApp`**: Contains code that will be shared across your Compose Multiplatform applications.
  - **`commonMain`**: Common code for all targets.
  - **Platform-specific folders**: Kotlin code compiled only for the platform indicated (e.g., `iosMain` for iOS-specific code).

* **`/iosApp`**: Contains the iOS application. Even if sharing your UI with Compose Multiplatform, you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* **`/shared`**: Contains code that will be shared between all targets in the project.
  - **`commonMain`**: The most important subfolder for shared code.
  - **Platform-specific folders**: Add code to these folders if it is specific to a particular platform.

## Libraries Used

- **Ktor**: For network requests to the SpaceX API.
- **Koin**: For dependency injection.
- **Room**: For local database storage.
- **Datastore**: For managing settings.
- **AndroidX Lifecycle ViewModel**: For sharing ViewModel across platforms.

## Features

- **Retrieve Data**: Fetch data from the SpaceX API using Ktor.
- **Local Storage**: Save data in a local Room database.
- **Display Data**: Display a list of SpaceX rocket launches with details such as launch date, results, and description.
- **Shared ViewModel**: Use a shared ViewModel for managing UI-related data in a lifecycle-conscious way across different platforms.

## Getting Started

1. **Clone the Repository**:
    ```sh
    git clone <repository_url>
    ```

2. **Open in IDE**: Open the project in your preferred IDE (e.g., IntelliJ IDEA).

3. **Configure Dependencies**:
   Ensure all dependencies are properly set up in your `build.gradle.kts` files for each target.

4. **Run the Project**:
  - For Android: Use the Android Studio to run the app on an emulator or device.
  - For iOS: Open the `iosApp` in Xcode and run it on a simulator or device.
  - For Desktop: Use IntelliJ IDEA to run the desktop application.

## Version Information

- **Ktor**: 2.3.11
- **Koin**: 3.6.0-alpha3
- **Room**: 2.7.0-alpha05
- **Datastore**: 1.1.1
- **AndroidX Lifecycle ViewModel**: 2.8.3

## Learn More

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Ktor](https://ktor.io/)
- [Koin](https://insert-koin.io/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [Datastore](https://developer.android.com/topic/libraries/architecture/datastore)

## References

- [Create a multiplatform app using Ktor and SQLDelight](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)
- [kotlin-multiplatform-samples](https://github.com/android/kotlin-multiplatform-samples)
