package mp.com.requestmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mp.com.models.Cast
import mp.com.models.Crew

/**
 * Created by Kashish on 13-08-2018.
 */
class MovieCreditRequest {
    @SerializedName("cast")
    @Expose()
    var castResult: List<mp.com.models.Cast>? = null

    @SerializedName("crew")
    @Expose()
    var crewResult: List<mp.com.models.Crew>? = null
}