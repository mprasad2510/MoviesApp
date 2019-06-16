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
import mp.com.viewholders.NowShowingViewHolder

/**
 * Created by Kashish on 14-08-2018.
 */
class NowShowingAdapter(private val listener: mp.com.interfaces.OnMovieClickListener,
                        private val mSharedPreferences: SharedPreferences) : PagedListAdapter<mp.com.database.entities.NowShowingEntry,
        RecyclerView.ViewHolder>(mp.com.adapters.NowShowingAdapter.Companion.REPO_COMPARATOR) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.movie_single_item, parent, false)
                this.context = parent.context
                return NowShowingViewHolder(view,context,listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

                val movie: mp.com.database.entities.NowShowingEntry? = getItem(position)
                if (movie != null){
                    val movieViewHolder = holder as NowShowingViewHolder
                    movieViewHolder.bindNowShowingData(movie,mSharedPreferences)
                } else{
                    notifyItemRemoved(position)
                }
    }


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<mp.com.database.entities.NowShowingEntry>() {
            override fun areItemsTheSame(oldItem: mp.com.database.entities.NowShowingEntry, newItem: mp.com.database.entities.NowShowingEntry): Boolean =
                    oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: mp.com.database.entities.NowShowingEntry, newItem: mp.com.database.entities.NowShowingEntry): Boolean =
                    oldItem == newItem
        }
    }
}