package mp.com.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log

/**
 * Created by Kashish on 11-08-2018.
 */
class FavouritesViewModel(application: Application) : AndroidViewModel(application) {

    private var movies: LiveData<MutableList<mp.com.database.entities.FavouritesEntry>>
    private val FavouritesViewModelTAG:String = "FavouritesViewModelTAG"

    init {
        val database: mp.com.database.AppDatabase = mp.com.database.AppDatabase.getInstance(this.getApplication())
        Log.d(FavouritesViewModelTAG,"Actively retrieving movies from database")
        movies = database.favouritesDao().loadAllFavourites()
    }

    fun getMovies(): LiveData<MutableList<mp.com.database.entities.FavouritesEntry>>{
        return movies
    }



}