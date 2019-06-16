# TheMovieDBApp 
Before you continue
------
* The App is still under development so this may contain some errors.<br>
* Generate your own TheMovieDb api [key](https://developers.themoviedb.org/3/getting-started/introduction) <br>
* Create a class named **API_KEY.kt** in [app\src\main\java\mp\com]
* Copy and paste following code with your API KEY :
  ```kotlin
  class API_KEY{
    companion object {
        val TMDB_API_KEY = "YOUR API KEY";
    }
  }
  ```
 * Create a new firebase [project](https://console.firebase.google.com) and then paste the generated **google-services.json** key in app directory
 * These are required steps, app won't build without these. 
 * Firebase is needed for crashlytics support. 
 * If you don't want to add firebase, just remove all firebase dependencies from gradle file.


Description
---------
* TheMovieDBApp is movies app built with android architecture components and the repository pattern (LiveData, ViewModel, Room, Paging).<br>
* TheMovieDb API is used to fetch movie details.

TO-DO's
---------
* Code clean up.
* Improve UI/UX.
