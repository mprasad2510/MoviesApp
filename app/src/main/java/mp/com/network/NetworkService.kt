package mp.com.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Kashish on 12-08-2018.
 */
//Singleton Class
class NetworkService private constructor() {
    private val mRetrofit: Retrofit
    private var mTmdbApi: mp.com.network.TMDBApi? = null

    val tmdbApi: mp.com.network.TMDBApi
        get() {
            if (mTmdbApi == null) {
                mTmdbApi = mRetrofit.create<mp.com.network.TMDBApi>(mp.com.network.TMDBApi::class.java)
            }
            return mTmdbApi!!
        }

    init {
        val client = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(loggingInterceptor)

        mRetrofit = Retrofit.Builder()
                .baseUrl(mp.com.network.NetworkService.Companion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
    }

    companion object {
        private val BASE_URL = "https://api.themoviedb.org/3/"
        private var mInstance: mp.com.network.NetworkService? = null

        val instance: mp.com.network.NetworkService
            get() {
                if (mp.com.network.NetworkService.Companion.mInstance == null) {
                    mp.com.network.NetworkService.Companion.mInstance = mp.com.network.NetworkService()
                }
                return mp.com.network.NetworkService.Companion.mInstance!!
            }
    }
}