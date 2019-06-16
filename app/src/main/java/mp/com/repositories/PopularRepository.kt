package mp.com.repositories

import android.arch.paging.LivePagedListBuilder

/**
 * Created by Kashish on 14-08-2018.
 */
class PopularRepository(
        private val service: mp.com.network.NetworkService,
        private val popularCache: mp.com.database.localcache.PopularLocalCache
) {

    fun popular(region: String): mp.com.database.databaseresults.PopularResults {

        val dataSourceFactory = popularCache.getAllPopular()

        val boundaryCallback = mp.com.boundarycallbacks.PopularBoundaryCallbacks(region, service, popularCache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        return mp.com.database.databaseresults.PopularResults(data, networkErrors)
    }



    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 60
    }

}