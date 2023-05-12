package com.example.mymovies.ui.fragment.home.genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Genre
import com.example.mymovies.R
import com.example.mymovies.databinding.ItemGenreListBinding
import com.example.mymovies.databinding.RecyclerItemBinding
import timber.log.Timber

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.UserViewHolder>() {

    var onItemClick: ((Genre) -> Unit)? = null

    private val mData = ArrayList<Genre>()

    fun setData(newListData: List<Genre>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): GenreAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_genre_list, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: GenreAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGenreListBinding.bind(itemView)
        fun bind(data: Genre) {
            with(binding) {

                Timber.d("check value image upcoming adapter : ${data.id}")
                tvGenreId.text = "Id: ${data.id}"
                tvGenreName.text = "Name: ${data.name}"
//                // concat string
//                ivPoster.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.id}")

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }
    }
}