package mp.com.adapters

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mp.com.R
import mp.com.viewholders.CastCrewViewHolder

/**
 * Created by Kashish on 03-08-2018.
 */
class CastAdapter(private val mSharedPreferences: SharedPreferences) : ListAdapter<mp.com.models.Cast,
        RecyclerView.ViewHolder>(mp.com.adapters.CastAdapter.Companion.REPO_COMPARATOR) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.cast_single_item, parent, false)
        this.context = parent.context
        return CastCrewViewHolder(view,context, mSharedPreferences)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val cast: mp.com.models.Cast? = getItem(holder.adapterPosition)
        if (cast != null){
            val movieViewHolder = holder as CastCrewViewHolder
            movieViewHolder.bindCastData(cast)
        }
    }


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<mp.com.models.Cast>() {
            override fun areItemsTheSame(oldItem: mp.com.models.Cast, newItem: mp.com.models.Cast): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: mp.com.models.Cast, newItem: mp.com.models.Cast): Boolean =
                    oldItem == newItem
        }
    }
}