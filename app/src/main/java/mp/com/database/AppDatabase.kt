package mp.com.database

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import android.util.Log

/**
 * Created by Kashish on 11-08-2018.
 */

@Database(entities = arrayOf(mp.com.database.entities.FavouritesEntry::class, mp.com.database.entities.SearchEntry::class,
        mp.com.database.entities.NowShowingEntry::class, mp.com.database.entities.PopularEntry::class,
        mp.com.database.entities.TopRatedEntry::class, mp.com.database.entities.UpcomingEntry::class), version = 1, exportSchema = false)
@TypeConverters(mp.com.database.converters.DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private val LOG_TAG: String = mp.com.database.AppDatabase::class.simpleName.toString()
        private val LOCK: Any = Object()
        private val DATABSE_NAME: String = "movies"
        @Volatile
        private var sInstance: mp.com.database.AppDatabase? = null

        fun getInstance(context: Context): mp.com.database.AppDatabase {
            if (mp.com.database.AppDatabase.Companion.sInstance == null){
                synchronized(mp.com.database.AppDatabase.Companion.LOCK){
                    Log.d(mp.com.database.AppDatabase.Companion.LOG_TAG,"Creating new database instance")
                    mp.com.database.AppDatabase.Companion.sInstance = Room.databaseBuilder(context.applicationContext,
                            mp.com.database.AppDatabase::class.java, mp.com.database.AppDatabase.Companion.DATABSE_NAME)
                            .build()
                }
            }
            Log.d(mp.com.database.AppDatabase.Companion.LOG_TAG,"Getting the database instance")
            return mp.com.database.AppDatabase.Companion.sInstance!!
        }

    }


    abstract fun favouritesDao(): mp.com.database.dao.FavouritesDao
    abstract fun nowShowingDao(): mp.com.database.dao.NowShowingDao
    abstract fun poplarDao(): mp.com.database.dao.PopularDao
    abstract fun searchDao(): mp.com.database.dao.SearchDao
    abstract fun topRatedDao(): mp.com.database.dao.TopRatedDao
    abstract fun upcomingDao(): mp.com.database.dao.UpcomingDao



    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}