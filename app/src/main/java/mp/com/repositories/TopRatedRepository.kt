package mp.com.repositories

import android.arch.paging.LivePagedListBuilder

/**
 * Created by Kashish on 14-08-2018.
 */
class TopRatedRepository(
        private val service: mp.com.network.NetworkService,
        private val topRatedCache: mp.com.database.localcache.TopRatedLocalCache
) {

    fun topRated(region: String): mp.com.database.databaseresults.TopRatedResults {

        val dataSourceFactory = topRatedCache.getAllTopRated()

        val boundaryCallback = mp.com.boundarycallbacks.TopRatedBoundaryCallbacks(region, service, topRatedCache)
        val networkErrors = boundaryCallback.networkErrors
        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        return mp.com.database.databaseresults.TopRatedResults(data, networkErrors)
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 60
    }


}