# NewsApp


A NewsApp for Android Users built with MVVM Clean Architecture and Jetpack Compose.

## TechStack Highlights

- **Jetpack Compose** for modern UI
- **single source of truth**
- **MVVM Clean architecture ** for clean and scalable codebase
- **Kotlin** and **Kotlin DSL**
- **Dagger Hilt** for efficient dependency injection.
- **Retrofit** for seamless networking
- **Room DB** for local storage of news articles
- **Coroutines** and **Flow** for asynchronous programming
- **StateFlow** for streamlined state management
- **Unit tests** and **UI tests** for robust code coverage
- **Instant search** for quick access to relevant news
- **Navigation** for smooth transitions between screens
- **WebView** for a seamless reading experience
- **Coil** for efficient image loading
- Pull to refresh for refreshing news content
- Swipe to delete for managing saved news articles


<p align="center">
<img alt="mvvm-architecture"  src="https://github.com/VISHNUONE8/NewsAppMVVMCleanArchitecture/blob/main/assets/mvvm_clean_arch.jpeg">
</p>


## Features Implemented

- Show top news articles
- Save news articles for future reference
- Search for specific news articles
- View news articles in a WebView for a detailed reading experience


## Dependency Use

- Jetpack Compose for UI: Modern UI toolkit for building native Android UIs
- Coil for Image Loading: Efficiently loads and caches images
- Retrofit for Networking: A type-safe HTTP client for smooth network requests
- Dagger Hilt for Dependency Injection: Simplifies dependency injection
- Room for Database: A SQLite object mapping library for local data storage
- Paging Compose for Pagination: Simplifies the implementation of paginated lists
- Mockito, JUnit for Testing: Ensures the reliability of the application

## How to Run the Project

- Clone the Repository:
```
git clone https://github.com/VISHNUONE8/NewsAppMVVMCleanArchitecture.git
cd NewsApp
```
- Visit newsapi.org and sign up for an API key, Copy the API key provided
- Open the build.gradle.kts file in the app module. Find the following line
```
buildConfigField("String", "API_KEY", "\"<Add your API Key>\"")
```
- Replace "Add your API Key" with the API key you obtained
- Build and run the NewsApp.

## The Complete Project Folder Structure

├── data
│   ├── api
│   │   └── NewsApiService.kt
│   ├── db
│   │   ├── ArticleDAO.kt
│   │   ├── ArticleDatabase.kt
│   │   └── Converters.kt
│   ├── model
│   │   ├── ApiResponse.kt
│   │   ├── Article.kt
│   │   └── Source.kt
│   ├── repository
│   │   ├── dataSource
│   │   │   ├── NewsLocalDataSource.kt
│   │   │   └── NewsRemoteDataSource.kt
│   │   └── dataSourceImpl
│   │       ├── NewsLocalDataSourceImpl.kt
│   │       └── NewsRemoteDataSourceImpl.kt
│   └── util
│       ├── Const.kt
│       ├── NoInternetException.kt
│       ├── ResultState.kt
│       └── ValidationUtil.kt
├── domain
│   ├── repository
│   │   ├── NewsRepository.kt
│   │   └── NewsRepositoryImpl.kt
│   └── usecase
│       ├── DeleteSavedNewsUseCase.kt
│       ├── GetNewsHeadlinesUseCase.kt
│       ├── GetSavedNewsUseCase.kt
│       ├── GetSearchedNewsUseCase.kt
│       ├── GetUSNewsHeadlinesUseCase.kt
│       └── SaveNewsUseCase.kt
└── presentation
├── NewsApp.kt
├── common
│   ├── NavigationUtil.kt
│   └── networkhelper
│       ├── NetworkHelper.kt
│       └── NetworkHelperImpl.kt
├── di
│   ├── DataBaseModule.kt
│   ├── FactoryModule.kt
│   ├── LocalDataModule.kt
│   ├── NetModule.kt
│   ├── NetworkModule.kt
│   ├── RemoteDataModule.kt
│   ├── RepositoryModule.kt
│   ├── UseCaseModule.kt
│   └── qualifiers.kt
└── ui
├── CommonUI.kt
├── MainActivity.kt
├── screens
│   ├── Article.kt
│   ├── ArticleScreen.kt
│   ├── NewsDestination.kt
│   ├── NewsNavigation.kt
│   ├── NewsScreen.kt
│   └── SavedNewsScreen.kt
├── theme
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
└── viewmodel
└── NewsViewModel.kt



<p align="center">
<img alt="screenshots"  src="https://github.com/VISHNUONE8/NewsAppMVVMCleanArchitecture/blob/main/assets/news_app_screens.jpeg">
</p>