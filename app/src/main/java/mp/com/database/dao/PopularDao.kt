package mp.com.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*

/**
 * Created by Kashish on 13-08-2018.
 */
@Dao
interface PopularDao {

    @Query("SELECT * FROM popular ORDER BY popularity DESC, voteCount Desc")
    fun loadAllPopular(): DataSource.Factory<Int, mp.com.database.entities.PopularEntry>

    @Query("SELECT * FROM popular WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfPopular(id: Int):Boolean

    @Insert
    fun insertPopular(popularEntry: mp.com.database.entities.PopularEntry)

    @Delete
    fun deletePopular(popularEntry: mp.com.database.entities.PopularEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searches: List<mp.com.database.entities.PopularEntry>)

    @Query("DELETE FROM popular")
    fun deleteAll()

    @Query("SELECT COUNT(movieId) FROM popular")
    fun getNumberOfRows(): Int

}