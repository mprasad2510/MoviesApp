package mp.com.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import mp.com.R

/**
 * Created by Kashish on 04-08-2018.
 */
class VideoViewHolder(itemView: View?,
                      val context: Context,
                      val movieVideoList: List<mp.com.models.MovieVideo>,
                      val listener: mp.com.interfaces.OnVideoClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var mVideoImage: ImageView
    init{
        mVideoImage = itemView!!.findViewById(R.id.activity_detail_trailer_poster_image)
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val position:Int = adapterPosition
        if (position!=RecyclerView.NO_POSITION){
            listener.onVideoClickListener(movieVideoList.get(position))
        }
    }

}