package com.example.mymovies.ui.fragment.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Genre
import com.example.core.domain.model.NowPlaying
import com.example.mymovies.databinding.FragmentHomeBinding
import com.example.mymovies.helper.Constant
import com.example.mymovies.ui.fragment.home.genre.GenreAdapter
import com.example.mymovies.ui.fragment.home.nowplaying.SliderAdapter
import com.example.mymovies.ui.fragment.home.popularmovies.PopularMoviesAdapter
import com.example.mymovies.ui.fragment.home.toprated.TopRatedAdapter
import com.example.mymovies.ui.fragment.home.upcoming.UpcomingAdapter
import com.example.mymovies.ui.activity.moviedetail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterPopularMovies : PopularMoviesAdapter
    private lateinit var adapterTopRated : TopRatedAdapter
    private lateinit var adapterUpcoming : UpcomingAdapter
    private lateinit var adapterGenre : GenreAdapter
    val homeFragmentViewModel : HomeFragmentViewModel by viewModels()

    private var currentPage = 0
    private var numPages = 0

    // param
    private var apiKey = Constant.API_KEY
    private var page = "1"
//    private var year = Calendar.getInstance().get(Calendar.YEAR).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserverNowPlaying(apiKey,page)
        setupObserverPopularMovies(apiKey,page)
        buildListPopularMovies()
        setupObserverTopRated(apiKey,page)
        buildListTopRated()
        setupObserverUpcoming(apiKey,page)
        buildListUpcoming()
        setupObserverGenre(apiKey)
        onclick()
    }

    // NowPlaying
    private fun setupObserverNowPlaying(apiKey: String, page: String) {

        homeFragmentViewModel.getNowPlaying(apiKey,page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
                        createSlider(data.data!!)
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

    private fun createSlider(string: List<NowPlaying>) {
        val adapterNowPlaying = SliderAdapter(this.requireContext(), string, homeFragmentViewModel)
        binding.vpSlider.adapter = adapterNowPlaying
        val density = resources.displayMetrics.density
        numPages = string.size

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
    }
    // NowPlaying

    // POPULAR MOVIES
    private fun setupObserverPopularMovies(apiKey: String,page: String) {

        homeFragmentViewModel.getPopularMovies(apiKey,page).observe(viewLifecycleOwner) { data ->

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
            startActivity(mIntent)
        }
    }
    // POPULAR MOVIES

    // TopRated
    private fun setupObserverTopRated(apiKey: String,page: String) {

        homeFragmentViewModel.getTopRated(apiKey,page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
                        adapterTopRated.setData(data.data)
                        Timber.tag(tag).d("observer_top_rated_adapter ${data.data}")
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

    private fun buildListTopRated() {
        adapterTopRated = TopRatedAdapter()
        binding.rvTopRated.setHasFixedSize(true)
        binding.rvTopRated.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRated.adapter = adapterTopRated

        binding.rvTopRated.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapterTopRated.onItemClick = { selectData ->
            val mIntent = Intent(activity, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            startActivity(mIntent)
        }
    }
    // TopRated

    // Upcoming
    private fun setupObserverUpcoming(apiKey: String,page: String) {

        homeFragmentViewModel.getUpcoming(apiKey,page).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
                        adapterUpcoming.setData(data.data)
                        Timber.tag(tag).d("observer_upcoming_adapter ${data.data}")
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

    private fun buildListUpcoming() {
        adapterUpcoming = UpcomingAdapter()
        binding.rvUpcoming.setHasFixedSize(true)
        binding.rvUpcoming.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcoming.adapter = adapterUpcoming

        binding.rvUpcoming.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapterUpcoming.onItemClick = { selectData ->
            val mIntent = Intent(activity, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            startActivity(mIntent)
        }
    }
    // Upcoming

    // Genre
    private fun setupObserverGenre(apiKey: String) {

        homeFragmentViewModel.getGenre(apiKey).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarHome.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarHome.visibility = View.GONE
//                        adapterGenre.setData(data.data)
                        buildListGenre(data.data!!)
                        Timber.tag(tag).d("observer_genre_adapter ${data.data}")
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


    private fun buildListGenre(genres: List<Genre>) {

        val genreName = ArrayList<String>()
        for (i in 0 until genres.size){
            genreName.add(genres[i].name)
        }


        val adapter = activity?.let {
            ArrayAdapter<String>(
                it,
                R.layout.simple_spinner_item, genreName
            )
        }
        adapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.setAdapter(adapter)

         binding.spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedGenre = parent.getItemAtPosition(position).toString()
                Toast.makeText(activity, "Genre:  $selectedGenre", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }
    // Genre


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