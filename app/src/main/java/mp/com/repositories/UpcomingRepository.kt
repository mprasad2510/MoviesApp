package mp.com.repositories

import android.arch.paging.LivePagedListBuilder

/**
 * Created by Kashish on 14-08-2018.
 */
class UpcomingRepository(
        private val service: mp.com.network.NetworkService,
        private val upcomingCache: mp.com.database.localcache.UpcomingLocalCache
) {


    /**
     * Search repositories whose names match the query.
     */
    fun upcoming(region: String): mp.com.database.databaseresults.UpcomingResults {
        val dataSourceFactory = upcomingCache.getAllUpcoming()

        val boundaryCallback = mp.com.boundarycallbacks.UpcomingBoundaryCallback(region, service, upcomingCache)
        val networkErrors = boundaryCallback.networkErrors
        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        // Get data from the local cache
        return mp.com.database.databaseresults.UpcomingResults(data, networkErrors)
    }



    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 60
    }

}