package mp.com.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import mp.com.repositories.SearchRepository

/**
 * Created by Kashish on 13-08-2018.
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val queryLiveData = MutableLiveData<String>()
    private val searchResult: LiveData<mp.com.database.databaseresults.SearchResults> = Transformations.map(queryLiveData, {
        repository.search(it)
    })

    val searches: LiveData<PagedList<mp.com.database.entities.SearchEntry>> = Transformations.switchMap(searchResult,
            { it -> it.data })
    val networkErrors: LiveData<String> = Transformations.switchMap(searchResult,
            { it -> it.networkErrors })

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }


    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = queryLiveData.value
}