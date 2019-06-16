package mp.com.database.localcache

import android.arch.paging.DataSource
import java.util.concurrent.Executor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

/**
 * Created by Kashish on 14-08-2018.
 */
class UpcomingLocalCache(
        private val upcomingDao: mp.com.database.dao.UpcomingDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of searches in the database, on a background thread.
     */
    fun insert(repos: List<mp.com.database.entities.UpcomingEntry>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            upcomingDao.insert(repos)
            insertFinished()
        }
    }

    fun getAllUpcoming(): DataSource.Factory<Int, mp.com.database.entities.UpcomingEntry> {
        return upcomingDao.loadAllUpcoming()
    }

    fun getAllItemsInUpcoming(): Int {
        val data  = runBlocking {
            async(CommonPool) {
                val numItems = upcomingDao.getNumberOfRows()
                return@async numItems
            }.await()
        }
        return data

    }

}