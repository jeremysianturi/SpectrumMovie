package com.example.mymovies.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.core.data.Resource
import com.example.core.domain.model.Banner
import com.example.mymovies.databinding.FragmentHomeBinding
import com.example.mymovies.ui.fragment.home.banner.SliderAdapter
import com.example.mymovies.ui.fragment.home.comingsoon.ComingSoonAdapter
import com.example.mymovies.ui.fragment.home.popularmovies.PopularMoviesAdapter
import com.example.mymovies.ui.fragment.moviedetail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterBanner : SliderAdapter
    private lateinit var adapterPopularMovies : PopularMoviesAdapter
    private lateinit var adapterComingSoon : ComingSoonAdapter
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()

    private var currentPage = 0
    private var numPages = 0


    // list of banner
    private lateinit var images : ArrayList<String>

    // param
    private var apiKey = "4e017aafa0c4da4d663bc40fa6d6afe0"
    private var language = "en-US"
    private var sortBy = "popularity.desc"
    private var includeAdult = false
    private var includeVideo = false
    private var page = "1"
    private var year = Calendar.getInstance().get(Calendar.YEAR).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserverBanner(apiKey,language,sortBy,includeAdult,includeVideo,page)
        setupObserverPopularMovies(apiKey,language,sortBy,includeAdult,includeVideo,page)
        buildListPopularMovies()
        setupObserverComingSoon(apiKey,language,sortBy,includeAdult,includeVideo,page,year)
        buildListComingSoon()

        images = ArrayList()

        // onclick
        onclick()

    }

    // BANNER
    private fun setupObserverBanner(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) {

        homeFragmentViewModel.getBanner(apiKey, language, sortBy, includeAdult, includeVideo, page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
//                        adapterBanner.setData(data.data)
                        getPosterUrl(data.data)
                        Timber.tag(tag).d("observer_business_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarHome.visibility = View.GONE
                        Timber.tag(tag).d("error_message ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }
    }

    private fun getPosterUrl(data : List<Banner>?){

        if (data != null) {

            for (i in 0 until data.size){
                images.add("https://image.tmdb.org/t/p/original${data[i].posterPath}")
            }
        }
        Timber.d("check isi images : $images")
        createSlider(images)
    }

    private fun createSlider(string: List<String>) {
//    private fun createSlider(string: List<Int>) {

        binding.vpSlider.adapter = SliderAdapter(this.requireContext(), string)
        binding.indicator.setViewPager(binding.vpSlider)
        val density = resources.displayMetrics.density
        //Set circle indicator radius
        binding.indicator.radius = 5 * density
        numPages = string.size
        // Auto getData of viewpager
        val update = Runnable {
            if (currentPage === numPages) {
                currentPage = 0
            }
            binding.vpSlider.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(update)
            }
        }, 5000, 5000)

        // Pager listener over indicator
        binding.indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }
    // BANNER

    // POPULAR MOVIES
    private fun setupObserverPopularMovies(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) {

        homeFragmentViewModel.getPopularMovies(apiKey, language, sortBy, includeAdult, includeVideo, page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
                        adapterPopularMovies.setData(data.data)
                        Timber.tag(tag).d("observer_popular_movies_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarHome.visibility = View.GONE
                        Timber.tag(tag).d("error_message ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }
    }

    private fun buildListPopularMovies() {

        adapterPopularMovies = PopularMoviesAdapter()
        binding.rvPopularMovies.setHasFixedSize(true)
        binding.rvPopularMovies.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovies.adapter = adapterPopularMovies

        binding.rvPopularMovies.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapterPopularMovies.onItemClick = { selectData ->
            val mIntent = Intent(activity, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            mIntent.putExtra("overview", selectData.overview)
            mIntent.putExtra("posterPath", selectData.posterPath)
            mIntent.putExtra("title", selectData.title)
            mIntent.putExtra("release_date", selectData.releaseDate)
            startActivity(mIntent)
        }
    }
    // POPULAR MOVIES

    // COMING SOON
    private fun setupObserverComingSoon(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String) {

        homeFragmentViewModel.getComingSoon(apiKey, language, sortBy, includeAdult, includeVideo, page,year).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
                        adapterComingSoon.setData(data.data)
                        Timber.tag(tag).d("observer_coming_soon_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarHome.visibility = View.GONE
                        Timber.tag(tag).d("error_message ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }
    }

    private fun buildListComingSoon() {

        adapterComingSoon = ComingSoonAdapter()
        binding.rvComingSoon.setHasFixedSize(true)
        binding.rvComingSoon.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvComingSoon.adapter = adapterComingSoon

        binding.rvComingSoon.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapterComingSoon.onItemClick = { selectData ->
            val mIntent = Intent(activity, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            mIntent.putExtra("overview", selectData.overview)
            mIntent.putExtra("posterPath", selectData.posterPath)
            mIntent.putExtra("title", selectData.title)
            mIntent.putExtra("release_date", selectData.releaseDate)
            startActivity(mIntent)
        }
    }
    // COMING SOON


    private fun onclick() {
        binding.apply {

        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}