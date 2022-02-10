package com.example.mymovies.ui.fragment.home.popularmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.PopularMovies
import com.example.mymovies.R
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.UserViewHolder>() {

    var onItemClick: ((PopularMovies) -> Unit)? = null

    private val mData = ArrayList<PopularMovies>()

    fun setData(newListData: List<PopularMovies>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): PopularMoviesAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: PopularMoviesAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerItemBinding.bind(itemView)
        fun bind(data: PopularMovies) {
            with(binding) {

                Timber.d("check value image popular movie adapter : ${data.posterPath}")
//                // concat string
//                tvContenttitle.text = "cuisine type: ${data.categoriesTitle}"
//                tvNameWahana.text = "Name: ${data.name}"
//                tvDateWahana.text = "Rating: ${data.rating}"
//                tvPostalCode.text = "Postal Code: ${data.locationZipCode}"
//                tvDistance.text = "Distance: ${data.distance}"
                ivPoster.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")
//                ivContentWahana.loadImage(itemView.context,data.thumbnail)



            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}