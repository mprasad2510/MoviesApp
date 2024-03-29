package mp.com.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mp.com.repositories.PopularRepository
import mp.com.viewmodels.PopularViewModel

/**
 * Created by Kashish on 14-08-2018.
 */
class ViewModelPopularFactory(private val repository: PopularRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PopularViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}