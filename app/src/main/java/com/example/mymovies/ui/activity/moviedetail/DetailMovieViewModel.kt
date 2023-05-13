package com.example.mymovies.ui.activity.moviedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.detailmovie.DetailMovieUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DetailMovieViewModel @ViewModelInject constructor(
    private val detailMovieUsecase: DetailMovieUsecase,
) : ViewModel() {

    // search by
//    val searchQuery = MutableStateFlow("")
//    private fun bannerFlow(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) = searchQuery.flatMapLatest { bannerUsecase.getSearchBusinesses(searchBy,it,sortBy) }
//    fun search(searchBy: String, sortBy: String) = businessesFlow(searchBy,sortBy).asLiveData()



    fun getDetailMovie(movieId: String, apiKey: String) =
        detailMovieUsecase.getDetailMovie(movieId, apiKey).asLiveData()

}