# MVVM-Example
Simple application to show how MVVM works

Development Environment -> The app is written entirely in Kotlin and uses the Gradle build system.


Architecture -> The architecture is built around Android Architecture Components. I followed the
recommendations laid out in the Guide to App Architecture when deciding on the architecture
for the app. I kept logic away from Activities and Fragments and moved it to ViewModels. I
observed data using LiveData and used the Data Binding Library to bind UI components in
layouts to the app's data sources.I used a Repository layer for handling data operations. App
data comes from different sources the repository is responsible for handling all data operations
and abstracting the data sources from the rest of the app (I liked using Room, but if I wanted to
swap it out for a different data source in the future, architecture allows us to do so in a clean
way).I used Room for offline storage. I used dagger-android for dependency injection.


Install Instructions -> Once project is downloaded, Import the project from Android Studio and simply run.
