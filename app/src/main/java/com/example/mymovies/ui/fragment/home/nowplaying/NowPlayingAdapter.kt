package com.example.mymovies.ui.fragment.home.nowplaying

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Genre
import com.example.core.domain.model.NowPlaying
import com.example.mymovies.R
import com.example.mymovies.databinding.AdapterSliderBinding
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import com.example.mymovies.ui.fragment.home.HomeFragmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.UserViewHolder>() {

    var onItemClick: ((NowPlaying) -> Unit)? = null

    private val mData = ArrayList<NowPlaying>()
    private lateinit var lifecycleOwner : LifecycleOwner
    private lateinit var homeViewModel : HomeFragmentViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setData(newListData: List<NowPlaying>?, lifecycleOwner: LifecycleOwner, homeViewModel: HomeFragmentViewModel) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        this.lifecycleOwner = lifecycleOwner
        this.homeViewModel = homeViewModel
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): NowPlayingAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_slider, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: NowPlayingAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdapterSliderBinding.bind(itemView)
        @OptIn(ExperimentalCoroutinesApi::class)
        fun bind(data: NowPlaying) {
            with(binding) {

                Timber.d("check value image upcoming adapter : ${data.posterPath}")

//                // concat string
                image.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")
                textview.text = data.title

                val genre = data.genreIds
                val genreArray = genre.filter { !it.isWhitespace() }.removeSurrounding("[", "]").split(",").map { it.toInt() }
                homeViewModel.genreQuery.value = genreArray

                val genreNames = ArrayList<String>()
                homeViewModel.search.observe(lifecycleOwner) { data ->
//                    for (i in 0 until genreArray.size) {
//                        println("humbalahum 2: ${data[i].name} dan size arraynya ${genreArray.size}")
//                        genreNames.add(data[i].name)
//                    }
//                    textviewGenre.text = genreNames.toString()
                    textviewGenre.text = data.toString()
                }

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }
    }
}