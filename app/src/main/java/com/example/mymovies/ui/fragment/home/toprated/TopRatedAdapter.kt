package com.example.mymovies.ui.fragment.home.toprated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.TopRated
import com.example.mymovies.R
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.UserViewHolder>() {

    var onItemClick: ((TopRated) -> Unit)? = null

    private val mData = ArrayList<TopRated>()

    fun setData(newListData: List<TopRated>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): TopRatedAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: TopRatedAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerItemBinding.bind(itemView)
        fun bind(data: TopRated) {
            with(binding) {

                Timber.d("check value image top rated adapter : ${data.posterPath}")

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