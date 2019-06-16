package mp.com.adapters

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mp.com.R
import mp.com.viewholders.ReviewViewHolder

/**
 * Created by Kashish on 02-08-2018.
 */
class MovieReviewAdapter(private val listener: mp.com.interfaces.OnReviewReadMoreClickListener) : ListAdapter<mp.com.models.MovieReview,
        RecyclerView.ViewHolder>(mp.com.adapters.MovieReviewAdapter.Companion.REPO_COMPARATOR) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.review_single_item, parent, false)
        this.context = parent.context
        return ReviewViewHolder(view,context,listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movie: mp.com.models.MovieReview? = getItem(position)
        if (movie != null){
            val movieViewHolder = holder as ReviewViewHolder
            movieViewHolder.bindReviewData(movie)
        }
    }


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<mp.com.models.MovieReview>() {
            override fun areItemsTheSame(oldItem: mp.com.models.MovieReview, newItem: mp.com.models.MovieReview): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: mp.com.models.MovieReview, newItem: mp.com.models.MovieReview): Boolean =
                    oldItem == newItem
        }
    }
}