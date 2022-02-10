package com.example.mymovies.ui.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class LovedViewModel @ViewModelInject constructor(
) : ViewModel() {

    // search by
//    val searchQuery = MutableStateFlow("")
//    private fun bannerFlow(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String) = searchQuery.flatMapLatest { bannerUsecase.getSearchBusinesses(searchBy,it,sortBy) }
//    fun search(searchBy: String, sortBy: String) = businessesFlow(searchBy,sortBy).asLiveData()


}