package com.example.mymovies.ui.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.genre.GenreUsecase
import com.example.core.domain.usecase.nowplaying.NowPlayingUsecase
import com.example.core.domain.usecase.popularmovies.PopularMoviesUsecase
import com.example.core.domain.usecase.toprated.TopRatedUsecase
import com.example.core.domain.usecase.upcoming.UpcomingUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class HomeFragmentViewModel @ViewModelInject constructor(
    private val nowPlayingUsecase: NowPlayingUsecase,
    private val popularMoviesUsecase: PopularMoviesUsecase,
    private val topRatedUsecase: TopRatedUsecase,
    private val upcomingUsecase: UpcomingUsecase,
    private val genreUsecase: GenreUsecase,
) : ViewModel() {

//     search by
//    val genreQuery = MutableStateFlow("")
//    private fun genreFlow(genreIds: List<Int>) = genreQuery.flatMapLatest { genreUsecase.getSearchGenreIds(genreIds,it,sortBy) }
//    fun search(searchBy: String, sortBy: String) = businessesFlow(searchBy,sortBy).asLiveData()

    fun getNowPlaying(apiKey: String, page: String) =
        nowPlayingUsecase.getNowPlaying(apiKey,page).asLiveData()

    fun getPopularMovies(apiKey: String,page: String) =
        popularMoviesUsecase.getPopularMovies(apiKey,page).asLiveData()

    fun getTopRated(apiKey: String,page: String) =
        topRatedUsecase.getTopRated(apiKey,page).asLiveData()

    fun getUpcoming(apiKey: String,page: String) =
        upcomingUsecase.getUpcoming(apiKey,page).asLiveData()

    fun getGenre(apiKey: String) =
        genreUsecase.getGenre(apiKey).asLiveData()

}