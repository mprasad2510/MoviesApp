package mp.com.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*

/**
 * Created by Kashish on 13-08-2018.
 */
@Dao
interface TopRatedDao {

    @Query("SELECT * FROM toprated ORDER BY voteCount DESC")
    fun loadAllToprated(): DataSource.Factory<Int, mp.com.database.entities.TopRatedEntry>

    @Query("SELECT * FROM toprated WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfToprated(id: Int):Boolean

    @Insert
    fun insertToprated(topRatedEntry: mp.com.database.entities.TopRatedEntry)

    @Delete
    fun deleteToprated(topRatedEntry: mp.com.database.entities.TopRatedEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searches: List<mp.com.database.entities.TopRatedEntry>)

    @Query("DELETE FROM toprated")
    fun deleteAll()

    @Query("SELECT COUNT(movieId) FROM toprated")
    fun getNumberOfRows(): Int

}