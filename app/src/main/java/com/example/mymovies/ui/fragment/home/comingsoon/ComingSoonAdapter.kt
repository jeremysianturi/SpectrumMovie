package com.example.mymovies.ui.fragment.home.comingsoon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.ComingSoon
import com.example.core.domain.model.PopularMovies
import com.example.mymovies.R
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class ComingSoonAdapter : RecyclerView.Adapter<ComingSoonAdapter.UserViewHolder>() {

    var onItemClick: ((ComingSoon) -> Unit)? = null

    private val mData = ArrayList<ComingSoon>()

    fun setData(newListData: List<ComingSoon>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ComingSoonAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ComingSoonAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerItemBinding.bind(itemView)
        fun bind(data: ComingSoon) {
            with(binding) {

                Timber.d("check value image coming soon adapter : ${data.posterPath}")

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