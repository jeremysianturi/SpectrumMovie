package com.example.mymovies.ui.fragment.home.nowplaying

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.PagerAdapter
import com.example.core.domain.model.NowPlaying
import com.example.mymovies.R
import com.example.mymovies.helper.Constant
import com.example.mymovies.helper.loadImage
import com.example.mymovies.ui.activity.moviedetail.DetailMovieActivity
import com.example.mymovies.ui.fragment.home.HomeFragment
import com.example.mymovies.ui.fragment.home.HomeFragmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SliderAdapter @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val context: Context, private val stringList: List<NowPlaying>, private val homeViewModel: HomeFragmentViewModel
) : PagerAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun destroyItem( container: ViewGroup, position: Int, `object`: Any ) {
        container.removeView(`object` as View)
    }

    override fun getCount() = stringList.size

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.adapter_slider, view, false)!!
        val imageView = imageLayout.findViewById<ImageView>(R.id.image)
        val textView = imageLayout.findViewById<TextView>(R.id.textview)
        val textViewGenre = imageLayout.findViewById<TextView>(R.id.textview_genre)

        imageView.loadImage(this.context, "${Constant.IMAGE_PREFIX_URL}${stringList[position].posterPath}")
        textView.text = stringList[position].title


        val genre = stringList[position].genreIds
        textViewGenre.text = genre

        imageView.setOnClickListener {
            val selectedData = stringList[position]
            val mIntent = Intent(context, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectedData.id)
            context.startActivity(mIntent)
        }

        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun isViewFromObject( view: View, `object`: Any ) = view == `object`

    override fun restoreState( state: Parcelable?, loader: ClassLoader? ) {

    }

    override fun saveState(): Parcelable? {
        return null
    }

}
