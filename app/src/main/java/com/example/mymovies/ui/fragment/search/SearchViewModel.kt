package com.example.mymovies.ui.fragment.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.search.SearchUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(
    private val searchUsecase: SearchUsecase,
) : ViewModel() {

    // search by
//    val searchQuery = MutableStateFlow("")
//    private fun bannerFlow(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) = searchQuery.flatMapLatest { nowPlayingUsecase.getSearchBusinesses(searchBy,it,sortBy) }
//    fun search(searchBy: String, sortBy: String) = businessesFlow(searchBy,sortBy).asLiveData()

    fun getSearch(apiKey: String, query: String, page: String) =
        searchUsecase.getSearch(apiKey,query,page).asLiveData()

}