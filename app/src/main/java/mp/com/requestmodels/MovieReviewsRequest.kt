package mp.com.requestmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mp.com.models.MovieReview

/**
 * Created by Kashish on 12-08-2018.
 */
class MovieReviewsRequest {
    @SerializedName("results")
    @Expose
    var reviews: List<mp.com.models.MovieReview>? = null
}