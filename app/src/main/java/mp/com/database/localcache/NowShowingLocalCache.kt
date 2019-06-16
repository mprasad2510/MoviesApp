package mp.com.database.localcache

import android.arch.paging.DataSource
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.Executor

/**
 * Created by Kashish on 14-08-2018.
 */
class NowShowingLocalCache(
        private val nowShowingDao: mp.com.database.dao.NowShowingDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of searches in the database, on a background thread.
     */
    fun insert(repos: List<mp.com.database.entities.NowShowingEntry>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            nowShowingDao.insert(repos)
            insertFinished()
        }
    }

    fun getAllNowShowing(): DataSource.Factory<Int, mp.com.database.entities.NowShowingEntry> {
        return nowShowingDao.loadAllNowShowing()
    }

    fun getAllItemsInNowShowing(): Int {
        val data  = runBlocking {
            async(CommonPool) {
                val numItems = nowShowingDao.getNumberOfRows()
                return@async numItems
            }.await()
        }
        return data

    }

}