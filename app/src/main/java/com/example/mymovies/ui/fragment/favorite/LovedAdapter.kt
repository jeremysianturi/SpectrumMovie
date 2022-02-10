package com.example.mymovies.ui.fragment.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.PopularMoviesGrid
import com.example.core.utils.LovedEntity
import com.example.mymovies.R
import com.example.mymovies.databinding.FavoriteItemListBinding
import com.example.mymovies.databinding.ItemListBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class LovedAdapter : RecyclerView.Adapter<LovedAdapter.UserViewHolder>() {

    var onItemClick: ((LovedEntity) -> Unit)? = null

    private val mData = ArrayList<LovedEntity>()

    fun setData(newListData: List<LovedEntity>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): LovedAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_list, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: LovedAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FavoriteItemListBinding.bind(itemView)
        fun bind(data: LovedEntity) {
            with(binding) {

                Timber.d("check value image popular movie grid adapter : $data")
                ivPosterFavorite.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")
                tvTitleFavorite.text = data.title
                tvYearFavorite.text = data.releaseDate

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}