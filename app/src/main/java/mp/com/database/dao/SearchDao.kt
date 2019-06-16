package mp.com.database.dao

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*

/**
 * Created by Kashish on 13-08-2018.
 */
@Dao
public interface SearchDao {

    @Query("SELECT * FROM search ORDER BY timeAdded ASC")
    fun loadAllSearch(): LiveData<MutableList<mp.com.database.entities.SearchEntry>>

    @Query("SELECT * FROM search WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfSearch(id: Int):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(searchEntry: mp.com.database.entities.SearchEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searches: List<mp.com.database.entities.SearchEntry>)

    @Delete
    fun deleteSearch(searchEntry: mp.com.database.entities.SearchEntry)

    @Query("SELECT * FROM search WHERE (title LIKE :queryString) ORDER BY timeAdded ASC")
    fun searchesByName(queryString: String): DataSource.Factory<Int, mp.com.database.entities.SearchEntry>

    @Query("DELETE FROM search")
    fun deleteAll()

    @Query("SELECT COUNT(movieId) FROM search")
    fun getNumberOfRows(): Int

}