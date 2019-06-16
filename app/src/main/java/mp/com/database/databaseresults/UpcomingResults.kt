package mp.com.database.databaseresults

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * Created by Kashish on 14-08-2018.
 */
data class UpcomingResults(
        val data: LiveData<PagedList<mp.com.database.entities.UpcomingEntry>>,
        val networkErrors: LiveData<String>
)