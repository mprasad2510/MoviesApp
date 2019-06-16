package mp.com.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*


/**
 * Created by Kashish on 13-08-2018.
 */
@Dao
interface UpcomingDao {

    @Query("SELECT * FROM upcoming ORDER BY timeAdded ASC")
    fun loadAllUpcoming(): DataSource.Factory<Int, mp.com.database.entities.UpcomingEntry>

    @Query("SELECT * FROM upcoming WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfUpcoming(id: Int):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(upcoming: List<mp.com.database.entities.UpcomingEntry>)

    @Insert
    fun insertUpcoming(upcomingEntry: mp.com.database.entities.UpcomingEntry)

    @Delete
    fun deleteUpcoming(upcomingEntry: mp.com.database.entities.UpcomingEntry)

    @Query("DELETE FROM upcoming")
    fun deleteAll()

    @Query("SELECT COUNT(movieId) FROM upcoming")
    fun getNumberOfRows(): Int


}