package com.example.mymovies.ui.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.banner.BannerUsecase
import com.example.core.domain.usecase.comingsoon.ComingSoonUsecase
import com.example.core.domain.usecase.popularmovies.PopularMoviesUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class HomeFragmentViewModel @ViewModelInject constructor(
    private val bannerUsecase: BannerUsecase,
    private val popularMoviesUsecase: PopularMoviesUsecase,
    private val comingSoonUsecase: ComingSoonUsecase,
) : ViewModel() {

    // search by
//    val searchQuery = MutableStateFlow("")
//    private fun bannerFlow(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) = searchQuery.flatMapLatest { bannerUsecase.getSearchBusinesses(searchBy,it,sortBy) }
//    fun search(searchBy: String, sortBy: String) = businessesFlow(searchBy,sortBy).asLiveData()



    fun getBanner(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) =
        bannerUsecase.getBanner(apiKey, language, sortBy, includeAdult, includeVideo, page).asLiveData()

    fun getPopularMovies(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) =
        popularMoviesUsecase.getPopularMovies(apiKey, language, sortBy, includeAdult, includeVideo, page).asLiveData()

    fun getComingSoon(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, year: String) =
        comingSoonUsecase.getComingSoon(apiKey, language, sortBy, includeAdult, includeVideo, page, year).asLiveData()

}