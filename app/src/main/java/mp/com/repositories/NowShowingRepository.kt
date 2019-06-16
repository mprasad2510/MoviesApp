package mp.com.repositories

import android.arch.paging.LivePagedListBuilder

/**
 * Created by Kashish on 14-08-2018.
 */
class NowShowingRepository(
        private val service: mp.com.network.NetworkService,
        private val nowShowingCache: mp.com.database.localcache.NowShowingLocalCache
) {

    fun nowShowing(region: String): mp.com.database.databaseresults.NowShowingResults {
        // Get data source factory from the local cache
        val dataSourceFactory = nowShowingCache.getAllNowShowing()

        val boundaryCallback = mp.com.boundarycallbacks.NowShowingBoundaryCallbacks(region, service, nowShowingCache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        return mp.com.database.databaseresults.NowShowingResults(data, networkErrors)
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 60
    }

}