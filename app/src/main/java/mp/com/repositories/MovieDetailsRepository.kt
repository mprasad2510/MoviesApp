package mp.com.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import mp.com.requestmodels.MovieCreditRequest
import mp.com.requestmodels.MovieReviewsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailsRepository(){

    private val service: mp.com.network.NetworkService = mp.com.network.NetworkService.instance

    fun getMovieDetails(movieId: String): LiveData<mp.com.models.MovieDetail>{

        val movieDetails:MutableLiveData<mp.com.models.MovieDetail> = MutableLiveData<mp.com.models.MovieDetail>()
        service.tmdbApi.getDetailMovie(movieId,"17e4458dbfa36cf74f579fa0b3c596e4","videos")
                .enqueue(object : Callback<mp.com.models.MovieDetail> {
                    override fun onFailure(call: Call<mp.com.models.MovieDetail>?, t: Throwable?) {
                        Log.i("MovieDetails Error","Details and Video fetch failed")
                    }

                    override fun onResponse(call: Call<mp.com.models.MovieDetail>?, response: Response<mp.com.models.MovieDetail>?) {
                        movieDetails.value = response!!.body()
                    }
                })
        return movieDetails
    }

    fun getMovieReviews(movieId: Long): LiveData<MovieReviewsRequest>{
        val movieReview:MutableLiveData<MovieReviewsRequest> = MutableLiveData<MovieReviewsRequest>()

        service.tmdbApi.getMovieReviews(movieId,"17e4458dbfa36cf74f579fa0b3c596e4")
                .enqueue(object : Callback<MovieReviewsRequest> {
                    override fun onFailure(call: Call<MovieReviewsRequest>?, t: Throwable?) {
                        Log.i("MovieDetails Error","Details and Video fetch failed")
                    }

                    override fun onResponse(call: Call<MovieReviewsRequest>?, response: Response<MovieReviewsRequest>?) {
                        movieReview.value = response!!.body()
                    }
                })
        return movieReview

    }

    fun getMovieCredit(movieId: Long): LiveData<MovieCreditRequest>{

        val movieCredit:MutableLiveData<MovieCreditRequest> = MutableLiveData<MovieCreditRequest>()
        service.tmdbApi.getMovieCredits(movieId,"17e4458dbfa36cf74f579fa0b3c596e4")
                .enqueue(object : Callback<MovieCreditRequest> {
                    override fun onFailure(call: Call<MovieCreditRequest>?, t: Throwable?) {
                        Log.i("MovieDetails Error","Details and Video fetch failed")
                    }

                    override fun onResponse(call: Call<MovieCreditRequest>?, response: Response<MovieCreditRequest>?) {
                        movieCredit.value = response!!.body()
                    }
                })
        return movieCredit
    }

}