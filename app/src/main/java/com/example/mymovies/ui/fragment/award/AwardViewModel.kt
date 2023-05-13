package com.example.mymovies.ui.fragment.award

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class AwardViewModel @ViewModelInject constructor(
    private val popularMoviesGridUsecase: PopularMoviesGridUsecase,
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val popularMoviesGridFlow = searchQuery.flatMapLatest {
        popularMoviesGridUsecase.getSearchPopularMoviesGrid(it)
    }

    val search = popularMoviesGridFlow.asLiveData()

    fun getPopularMoviesGrid(apiKey: String,page: String) =
        popularMoviesGridUsecase.getPopularMoviesGrid(apiKey,page).asLiveData()

}