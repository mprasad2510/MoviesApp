package mp.com

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import mp.com.repositories.*
import java.util.concurrent.Executors

object Injection {

    //Search
    private fun provideSearchCache(context: Context): mp.com.database.localcache.SearchLocalCache {
        val database = mp.com.database.AppDatabase.getInstance(context)
        return mp.com.database.localcache.SearchLocalCache(database.searchDao(), Executors.newSingleThreadExecutor())
    }
    private fun provideSearchRepository(context: Context): SearchRepository {
        return SearchRepository(mp.com.network.NetworkService.instance, mp.com.Injection.provideSearchCache(context))
    }
    fun provideSearchViewModelFactory(context: Context): ViewModelProvider.Factory {
        return mp.com.viewmodelfactory.ViewModelSearchFactory(mp.com.Injection.provideSearchRepository(context))
    }

    //NowShowing
    private fun provideNowShowingCache(context: Context): mp.com.database.localcache.NowShowingLocalCache {
        val database = mp.com.database.AppDatabase.getInstance(context)
        return mp.com.database.localcache.NowShowingLocalCache(database.nowShowingDao(), Executors.newSingleThreadExecutor())
    }
    private fun provideNowShowingRepository(context: Context): NowShowingRepository {
        return NowShowingRepository(mp.com.network.NetworkService.instance, mp.com.Injection.provideNowShowingCache(context))
    }
    fun provideNowShowingViewModelFactory(context: Context): ViewModelProvider.Factory {
        return mp.com.viewmodelfactory.ViewModelNowShowingFactory(mp.com.Injection.provideNowShowingRepository(context))
    }

    //Upcoming
    private fun provideUpcomingCache(context: Context): mp.com.database.localcache.UpcomingLocalCache {
        val database = mp.com.database.AppDatabase.getInstance(context)
        return mp.com.database.localcache.UpcomingLocalCache(database.upcomingDao(), Executors.newSingleThreadExecutor())
    }
    private fun provideUpcomingRepository(context: Context): UpcomingRepository {
        return UpcomingRepository(mp.com.network.NetworkService.instance, mp.com.Injection.provideUpcomingCache(context))
    }
    fun provideUpcomingViewModelFactory(context: Context): ViewModelProvider.Factory {
        return mp.com.viewmodelfactory.ViewModelUpcomingFactory(mp.com.Injection.provideUpcomingRepository(context))
    }

    //Popular
    private fun providePopularCache(context: Context): mp.com.database.localcache.PopularLocalCache {
        val database = mp.com.database.AppDatabase.getInstance(context)
        return mp.com.database.localcache.PopularLocalCache(database.poplarDao(), Executors.newSingleThreadExecutor())
    }
    private fun providePopularRepository(context: Context): PopularRepository {
        return PopularRepository(mp.com.network.NetworkService.instance, mp.com.Injection.providePopularCache(context))
    }
    fun providePopularViewModelFactory(context: Context): ViewModelProvider.Factory {
        return mp.com.viewmodelfactory.ViewModelPopularFactory(mp.com.Injection.providePopularRepository(context))
    }

    //TopRated
    private fun provideTopRatedCache(context: Context): mp.com.database.localcache.TopRatedLocalCache {
        val database = mp.com.database.AppDatabase.getInstance(context)
        return mp.com.database.localcache.TopRatedLocalCache(database.topRatedDao(), Executors.newSingleThreadExecutor())
    }
    private fun provideTopRatedRepository(context: Context): TopRatedRepository {
        return TopRatedRepository(mp.com.network.NetworkService.instance, mp.com.Injection.provideTopRatedCache(context))
    }
    fun provideTopRatedViewModelFactory(context: Context): ViewModelProvider.Factory {
        return mp.com.viewmodelfactory.ViewModelTopRatedFactory(mp.com.Injection.provideTopRatedRepository(context))
    }

    //Details
    fun provideMovieDetailsRepository(): ViewModelProvider.Factory{
        val movieDetailsrepo = MovieDetailsRepository()
        return mp.com.viewmodelfactory.ViewModelDetailFactory(movieDetailsrepo)
    }

}