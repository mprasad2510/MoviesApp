package mp.com.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*

/**
 * Created by Kashish on 13-08-2018.
 */
@Dao
 interface NowShowingDao {

    @Query("SELECT * FROM nowshowing ORDER BY timeAdded ASC")
    fun loadAllNowShowing(): DataSource.Factory<Int, mp.com.database.entities.NowShowingEntry>

    @Query("SELECT * FROM nowshowing WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfNowShowing(id: Int):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowShowing(nowShowingEntry: mp.com.database.entities.NowShowingEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searches: List<mp.com.database.entities.NowShowingEntry>)

    @Delete
    fun deleteNowShowing(nowShowingEntry: mp.com.database.entities.NowShowingEntry)

    @Query("DELETE FROM nowshowing")
    fun deleteAll()

    @Query("SELECT COUNT(movieId) FROM nowshowing")
    fun getNumberOfRows(): Int

}