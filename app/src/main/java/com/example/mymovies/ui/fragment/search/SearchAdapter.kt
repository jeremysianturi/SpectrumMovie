package com.example.mymovies.ui.fragment.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Search
import com.example.core.domain.model.Upcoming
import com.example.mymovies.R
import com.example.mymovies.databinding.ItemListBinding
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.UserViewHolder>() {

    var onItemClick: ((Search) -> Unit)? = null

    private val mData = ArrayList<Search>()

    fun setData(newListData: List<Search>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): SearchAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: SearchAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: Search) {
            with(binding) {

                Timber.d("check value image popular movie grid adapter : ${data.posterPath}")
                ivPosterPopular.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")
                tvTitlePopular.text = data.title
                tvCastPopular.text = data.genreIds

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }
    }
}