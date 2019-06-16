package mp.com.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import mp.com.R

/**
 * Created by Kashish on 02-08-2018.
 */
class ReviewViewHolder(itemView: View?, context: Context,
                       val listener: mp.com.interfaces.OnReviewReadMoreClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var mReviewAuthor: TextView
    var mReviewContent: TextView
    var mReadMoreButton: TextView
    private var mReview: mp.com.models.MovieReview? = null

    init{
        mReviewAuthor = itemView!!.findViewById(R.id.review_author_name)
        mReviewContent = itemView.findViewById(R.id.review_content)
        mReadMoreButton = itemView.findViewById(R.id.review_read_more)
        itemView.setOnClickListener(this)
    }

    fun bindReviewData(movie: mp.com.models.MovieReview?) {
        if (movie == null) {
            return
        } else {
            this.mReview = movie
            mReviewAuthor.setText(mReview!!.author)
            mReviewContent.setText(mReview!!.content)

        }
    }

    override fun onClick(p0: View?) {
        val position: Int = adapterPosition
        if (position!=RecyclerView.NO_POSITION){
                listener.onReviewReadMoreClickListener(mReview!!)
            }
        }
}
