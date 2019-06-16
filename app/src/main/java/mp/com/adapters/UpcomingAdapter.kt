package mp.com.adapters

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mp.com.R
import mp.com.viewholders.UpcomingViewHolder

/**
 * Created by Kashish on 15-08-2018.
 */
class UpcomingAdapter(private val listener: mp.com.interfaces.OnMovieClickListener,
                      private val mSharedPreferences: SharedPreferences) : PagedListAdapter<mp.com.database.entities.UpcomingEntry,
        RecyclerView.ViewHolder>(mp.com.adapters.UpcomingAdapter.Companion.REPO_COMPARATOR) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_single_item, parent, false)
        this.context = parent.context
        return UpcomingViewHolder(view,context,listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movie: mp.com.database.entities.UpcomingEntry? = getItem(position)

        if (movie != null){
            val movieViewHolder = holder as UpcomingViewHolder
            movieViewHolder.bindNowShowingData(movie,mSharedPreferences)
        } else{
            notifyItemRemoved(position)
        }

    }


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<mp.com.database.entities.UpcomingEntry>() {
            override fun areItemsTheSame(oldItem: mp.com.database.entities.UpcomingEntry, newItem: mp.com.database.entities.UpcomingEntry): Boolean =
                    oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: mp.com.database.entities.UpcomingEntry, newItem: mp.com.database.entities.UpcomingEntry): Boolean =
                    oldItem == newItem
        }
    }
}