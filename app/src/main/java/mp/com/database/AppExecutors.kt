package mp.com.database

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Kashish on 11-08-2018.
 */
class  AppExecutors {

    companion object {
        private val LOCK: Any = Object()
        private var sInstance: mp.com.database.AppExecutors? = null


        private class MainThreadExecutor: Executor {

            private var mainThreadHandler: Handler = Handler(Looper.getMainLooper())

            override fun execute(p0: Runnable?) {
                mainThreadHandler.post(p0)
            }

        }

        fun getInstance(): mp.com.database.AppExecutors {
            if (mp.com.database.AppExecutors.Companion.sInstance == null){
                synchronized(mp.com.database.AppExecutors.Companion.LOCK){
                    mp.com.database.AppExecutors.Companion.sInstance = mp.com.database.AppExecutors(Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            mp.com.database.AppExecutors.Companion.MainThreadExecutor())
                }
            }
            return mp.com.database.AppExecutors.Companion.sInstance!!
        }

    }

    private var diskIO: Executor? = null
    private var mainThread: Executor? = null
    private var networkIO: Executor? = null

    constructor(diskIO: Executor, networkIO: Executor, mainThread: Executor){
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }

    fun diskIO(): Executor = diskIO!!
    fun mainThread(): Executor = mainThread!!
    fun networkIO(): Executor = networkIO!!

}