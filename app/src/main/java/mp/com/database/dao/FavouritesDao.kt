package mp.com.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by Kashish on 11-08-2018.
 */

@Dao
    interface FavouritesDao {

    @Query("SELECT * FROM favourites ORDER BY timeAdded DESC")
    fun loadAllFavourites(): LiveData<MutableList<mp.com.database.entities.FavouritesEntry>>

    @Query("SELECT * FROM favourites WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfFavourite(id: Int):LiveData<MutableList<mp.com.database.entities.FavouritesEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favouritesEntry: mp.com.database.entities.FavouritesEntry)

    @Delete
    fun deleteFavourite(favouritesEntry: mp.com.database.entities.FavouritesEntry)

    @Query("SELECT COUNT(movieId) FROM favourites")
    fun getNumberOfRows(): Int

}