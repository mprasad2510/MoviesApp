package mp.com.database.databaseresults

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * Created by Kashish on 13-08-2018.
 */
data class SearchResults(
        val data: LiveData<PagedList<mp.com.database.entities.SearchEntry>>,
        val networkErrors: LiveData<String>
)