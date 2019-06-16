package mp.com.database.databaseresults

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList


data class PopularResults(
        val data: LiveData<PagedList<mp.com.database.entities.PopularEntry>>,
        val networkErrors: LiveData<String>
)