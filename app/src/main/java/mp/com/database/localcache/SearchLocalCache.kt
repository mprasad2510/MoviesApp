package mp.com.database.localcache

import android.arch.paging.DataSource
import java.util.concurrent.Executor

/**
 * Created by Kashish on 13-08-2018.
 */
class SearchLocalCache(
        private val searchDao: mp.com.database.dao.SearchDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of searches in the database, on a background thread.
     */
    fun insert(repos: List<mp.com.database.entities.SearchEntry>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            searchDao.insert(repos)
            insertFinished()
        }
    }

    fun searchesByName(name: String): DataSource.Factory<Int, mp.com.database.entities.SearchEntry> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return searchDao.searchesByName(query)
    }
}