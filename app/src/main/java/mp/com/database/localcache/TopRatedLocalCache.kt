package mp.com.database.localcache

import android.arch.paging.DataSource
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.Executor

/**
 * Created by Kashish on 14-08-2018.
 */
class TopRatedLocalCache(
        private val topRatedDao: mp.com.database.dao.TopRatedDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of searches in the database, on a background thread.
     */
    fun insert(repos: List<mp.com.database.entities.TopRatedEntry>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            topRatedDao.insert(repos)
            insertFinished()
        }
    }

    fun getAllTopRated(): DataSource.Factory<Int, mp.com.database.entities.TopRatedEntry> {
        return topRatedDao.loadAllToprated()
    }

    fun getAllItemsInTopRated(): Int {
        val data  = runBlocking {
            async(CommonPool) {
                val numItems = topRatedDao.getNumberOfRows()
                return@async numItems
            }.await()
        }
        return data

    }

}