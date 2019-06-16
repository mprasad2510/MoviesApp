package mp.com.database.localcache

import android.arch.paging.DataSource
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.Executor

/**
 * Created by Kashish on 14-08-2018.
 */
class PopularLocalCache(
        private val popularDao: mp.com.database.dao.PopularDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of searches in the database, on a background thread.
     */
    fun insert(repos: List<mp.com.database.entities.PopularEntry>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            popularDao.insert(repos)
            insertFinished()
        }
    }

    fun getAllPopular(): DataSource.Factory<Int, mp.com.database.entities.PopularEntry> {
        return popularDao.loadAllPopular()
    }

    fun getAllItemsInPopular(): Int {
        val data  = runBlocking {
            async(CommonPool) {
                val numItems = popularDao.getNumberOfRows()
                return@async numItems
            }.await()
        }
        return data

    }

}