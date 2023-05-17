package com.example.mymovies.ui.activity.moviedetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.core.data.Resource
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.domain.model.DetailMovie
import com.example.core.domain.model.PopularMovies
import com.example.core.utils.LovedEntity
import com.example.mymovies.databinding.ActivityDetailMovieBinding
import com.example.mymovies.helper.Constant
import com.example.mymovies.helper.loadImage
import com.example.mymovies.ui.fragment.LovedViewModel
import com.example.mymovies.ui.fragment.home.HomeFragmentViewModel
import com.example.mymovies.ui.fragment.home.genre.GenreAdapter
import com.example.mymovies.ui.fragment.home.popularmovies.PopularMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel : DetailMovieViewModel by viewModels()
    private lateinit var adapterGenre : GenreAdapter

    private var dataDetailMovie : List<DetailMovie>? = null
    private var apiKey = Constant.API_KEY
    private lateinit var idChoosen: String
    private lateinit var posterPath: String
    private lateinit var releaseDate1: String

    // loved
    lateinit var db: MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
//        actionbar?.title = "Detail Movie"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        db = Room.databaseBuilder(applicationContext, MovieDatabase::class.java, "loved-db").build()

        idChoosen = intent.getIntExtra("id_choosen",520763).toString()
        Timber.d("check value extra data : \n idChoosen: $idChoosen ")

        setupObserver(idChoosen,apiKey)
        onclick()

    }

    private fun setupObserver(movieId: String, apiKey: String) {

        detailMovieViewModel.getDetailMovie(movieId, apiKey).observe(this) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarDetailMovie.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarDetailMovie.visibility = View.GONE
                        dataDetailMovie = data.data
//                        adapterGenre.setData(data.data.get(0).genresName)
                        setView()
                        Timber.d("observer_detail_movie_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarDetailMovie.visibility = View.GONE
                        Timber.d("error_message detail ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(supportFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }
    }

    private fun buildListGenre() {
        adapterGenre = GenreAdapter()
        binding.rvGenre.setHasFixedSize(true)
        binding.rvGenre.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGenre.adapter = adapterGenre

        binding.rvGenre.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
    }

    private fun setView(){
        binding.apply {
            tvStatus.text = dataDetailMovie?.get(0)?.status
            ivPosterDetail.loadImage(this@DetailMovieActivity,"https://image.tmdb.org/t/p/original${dataDetailMovie?.get(0)?.posterPath}")
            tvTitle.text = dataDetailMovie?.get(0)?.title
            tvOverview.text = dataDetailMovie?.get(0)?.overview
            ivBackdrop.loadImage(this@DetailMovieActivity,"https://image.tmdb.org/t/p/original${dataDetailMovie?.get(0)?.backdropPath}")
            tvReleasedate.text = dataDetailMovie?.get(0)?.releaseDate
            tvVoteaverage.text = dataDetailMovie?.get(0)?.voteAverage.toString()
            tvVotecount.text = dataDetailMovie?.get(0)?.voteCount.toString()
            tvLanguages.text = dataDetailMovie?.get(0)?.spokenLanguagesName.toString()
            buildListGenre()

            title = dataDetailMovie?.get(0)?.title
            releaseDate1 = dataDetailMovie?.get(0)?.releaseDate.toString()
            posterPath = dataDetailMovie?.get(0)?.posterPath.toString()
        }
    }

    private fun onclick() {
        binding.apply {

            btnAddToFavorite.setOnClickListener {
                GlobalScope.launch {
                    fillEntity()
                }
                finish()
            }
        }
    }

    private fun fillEntity() {

//        val idLoved = dataDetailMovie?.get(0)?.id     // data api belom lengkap
        val idLoved = idChoosen
//        val titleLoved = dataDetailMovie?.get(0)?.title       // data api belom lengkap
        val titleLoved = title.toString()
//        val releaseDate = dataDetailMovie?.get(0)?.releaseDate    // data api belom lengkap
        val releaseDate = releaseDate1
        val genreName = dataDetailMovie?.get(0)?.genresName
        val posterPath = posterPath

        Timber.d("check value chosen row: \n idLoved: $idLoved \n titleLoved: $titleLoved \n releaseDate: $releaseDate \n genreName: $genreName \n posterPath: $posterPath")


        val loved = LovedEntity(idLoved.toInt(), titleLoved, releaseDate, genreName.toString(), posterPath)
        db.lovedDao().insert(loved)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}