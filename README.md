# Smart Pantry Manager

Smart Pantry Manager is a Java Android application developed to help users manage pantry ingredients and find recipes based on the ingredients and quantities they have available.

## Features

* Add pantry ingredients
* Edit pantry ingredients
* Delete pantry ingredients
* Store ingredient quantities and units
* Record optional expiry dates
* Browse 20 pre-loaded recipes
* View recipe ingredients and preparation methods
* Get recipe suggestions based on pantry contents
* Strict ingredient and quantity matching
* Feedback when no recipes match
* SQLite database persistence
* Settings and profile screen
* Light and dark mode

## Technologies Used

* Java
* Android Studio
* SQLite
* RecyclerView and Adapter
* AndroidX
* Gradle

## Database

SQLite was chosen because the application is designed to store pantry and recipe information locally on the Android device. SQLite is lightweight, does not require a separate server, and provides persistent storage for the application's structured data.

## Setup and Running the Application

1. Download or clone the Smart Pantry Manager repository.
2. Open the `SmartPantryManager2` project folder in Android Studio.
3. Allow Android Studio to complete the Gradle sync.
4. Connect an Android device with USB debugging enabled, or use an Android emulator.
5. Run the application from Android Studio.

The application uses a minimum Android SDK of API 28.

## Recipe Matching

The application uses strict recipe matching. A recipe is suggested only when every required ingredient is available in the pantry in a sufficient quantity.

For example, if a recipe requires 30 g of sugar and the pantry contains only 20 g, that recipe will not be suggested.

## Database Persistence

Pantry ingredients are stored in the SQLite database. This means that ingredients remain available after the application is closed and reopened.

The application also stores the pre-loaded recipes and their required ingredients in the database.

## Project Information

**Application:** Smart Pantry Manager
**Module:** Mobile App Development 700
**Language:** Java
**Database:** SQLite
**Version:** 1.0
