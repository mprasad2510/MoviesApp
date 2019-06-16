package mp.com.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mp.com.repositories.MovieDetailsRepository
import mp.com.requestmodels.MovieCreditRequest
import mp.com.requestmodels.MovieReviewsRequest


/**
 * Created by Kashish on 15-08-2018.
 */
class MovieDetailsViewModel(private val detailsRepo: MovieDetailsRepository): ViewModel() {

    private var movieDetail: LiveData<mp.com.models.MovieDetail>? = null
    private var movieReviews: LiveData<MovieReviewsRequest>? = null
    private var movieCredit: LiveData<MovieCreditRequest>? = null

    fun getDetails(movieId: String): LiveData<mp.com.models.MovieDetail>{
        if (movieDetail == null){
            movieDetail = detailsRepo.getMovieDetails(movieId)
        }
        return movieDetail!!
    }

    fun getReviews(movieId: Long): LiveData<MovieReviewsRequest>{
        if (movieReviews == null){
            movieReviews = detailsRepo.getMovieReviews(movieId)
        }
        return movieReviews!!
    }

    fun getCredits(movieId: Long): LiveData<MovieCreditRequest>{
        if (movieCredit == null){
            movieCredit = detailsRepo.getMovieCredit(movieId)
        }
        return movieCredit!!
    }

}