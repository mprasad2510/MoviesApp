package mp.com.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import mp.com.repositories.NowShowingRepository

/**
 * Created by Kashish on 14-08-2018.
 */
class NowShowingViewModel(private val repository: NowShowingRepository ) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    private val nowShowingResult: LiveData<mp.com.database.databaseresults.NowShowingResults> = Transformations.map(queryLiveData, {
        repository.nowShowing(it)
    })

    val nowshowing: LiveData<PagedList<mp.com.database.entities.NowShowingEntry>> = Transformations.switchMap(nowShowingResult,
            { it -> it.data })
    val networkErrors: LiveData<String> = Transformations.switchMap(nowShowingResult,
            { it -> it.networkErrors })

    fun getNowShowing(region: String) {
        queryLiveData.value = region
    }

}