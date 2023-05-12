package com.example.mymovies.ui.fragment.home.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Upcoming
import com.example.mymovies.R
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.UserViewHolder>() {

    var onItemClick: ((Upcoming) -> Unit)? = null

    private val mData = ArrayList<Upcoming>()

    fun setData(newListData: List<Upcoming>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): UpcomingAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: UpcomingAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerItemBinding.bind(itemView)
        fun bind(data: Upcoming) {
            with(binding) {

                Timber.d("check value image upcoming adapter : ${data.posterPath}")

//                // concat string
                ivPoster.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }
    }
}