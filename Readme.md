## 🐙 Github Repos app (Android)

**Github Explorer** is a sleek Android application built with Kotlin that allows users to effortlessly explore Github repositories, dive into their details, and track related issues.  Leveraging the power of the Github API, the app delivers a seamless and informative user experience.


[![GITHUB API](https://img.shields.io/badge/GITHUB_API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Kotlin](https://img.shields.io/badge/kotlin-1.8.0-blue.svg)](http://kotlinlang.org)
[![Hilt](https://img.shields.io/badge/Hilt-2.44-orange)](https://developer.android.com/training/dependency-injection/hilt-android)
[![Retrofit](https://img.shields.io/badge/Retrofit-2.9.0-red)](https://square.github.io/retrofit/)
[![Room](https://img.shields.io/badge/Room-2.4.3-green)](https://developer.android.com/jetpack/androidx/releases/room)
[![DataStore](https://img.shields.io/badge/DataStore-1.0.0-blue)](https://developer.android.com/topic/libraries/architecture/datastore)
[![Kotlin Coroutines](https://img.shields.io/badge/Kotlin%20Coroutines-1.6.4-purple)](https://kotlinlang.org/docs/coroutines-overview.html)
[![Compose](https://img.shields.io/badge/Compose-1.2.0-brightgreen)](https://developer.android.com/jetpack/compose)
[![Shimmer](https://img.shields.io/badge/Shimmer-0.5.0-yellow)](https://github.com/facebook/shimmer-android) 


## 📸 Screenshots

<p align="center">
  
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_11.png?raw=true" width="250" hspace="20" alt="Repos Main Screen">
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_3.png?raw=true" width="250" hspace="20" alt="Repos Main Screen Shimmer">
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_1.png?raw=true" width="250" hspace="20" alt="Repo Details screen">
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_4.png?raw=true" width="250" hspace="20" alt="Repo Details screen with place holder">
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_2.png?raw=true" width="250" hspace="20" alt="Repo issues screen">
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_5.png?raw=true" width="250" hspace="20" alt="Repo issues screen Shimmer">

  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_7.png?raw=true" width="250" hspace="20" alt="Error screen">
  <img src="https://github.com/darsh-7/Github_Repos_App/blob/master/screen_shots/Screenshot_8.png?raw=true" width="250" hspace="20" alt="Repos main screen with cached deta">
  
</p>


## ✨ Features


* **Repository Browsing:** Explore a handpicked collection of interesting Github repositories.
* **Repository Details:**  Get comprehensive information for each repository:
    * Name, description, owner, stars, forks
    * Complete list of open issues
* **Issue Exploration:**  View details for open issues within a repository:
    * Title, description, author, creation date, comments
* **Offline Caching:**  Previously viewed repositories and their details are available offline, thanks to Room persistence.
* **Smooth UI:** Experience a responsive and visually appealing interface with delightful Shimmer loading animations. 


## 🚀 Technologies Used

* **Kotlin:** Modern and concise programming language for Android development.
* **Kotlin Coroutines:** Manage asynchronous operations efficiently.
* **Android Jetpack:** 
    * **Room:**  Persist repository data locally for offline access.
    * **DataStore:**  Store small amounts of data, like first-time user flags.
    * **ViewModel:**  Manage UI-related data and handle user interactions.
* **Hilt:** Streamlined dependency injection framework for Android.
* **Retrofit:**  Type-safe HTTP client for consuming the Github API.
* **Kotlin Compose:** Modern and declarative UI toolkit for building beautiful and responsive interfaces.
* **Shimmer:**  Add beautiful loading animations to enhance the user experience.
* **SOLID Principles:**  Adheres to SOLID principles for a robust and maintainable codebase.
* **MVVM (Model-View-ViewModel):** Clean architecture pattern for separating concerns and improving testability. 

## 🏗️ Architecture

The app is designed following the **MVVM (Model-View-ViewModel)** architectural pattern:

* **Model:** Data handling logic, including API interactions, data parsing, and database operations.
* **View:**  UI components responsible for displaying data and capturing user input.
* **ViewModel:**  Acts as an intermediary between the Model and View, preparing and managing data for the UI.

## 🛠️ Building and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/darsh-7/Github_Repos_App.git
   ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device. 


This project was created as a learning experience to explore and implement modern Android development practices. Feel free to fork, modify, and experiment with the code! 
