package com.example.core.domain.usecase.search

import com.example.core.data.Resource
import com.example.core.data.repository.SearchRepository
import com.example.core.data.repository.UpcomingRepository
import com.example.core.domain.model.Search
import com.example.core.domain.model.Upcoming
import com.example.core.domain.usecase.upcoming.UpcomingUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) :
    SearchUsecase {

    override fun getSearch(apiKey: String, query: String, page: String): Flow<Resource<List<Search>>> =
        searchRepository.getSearch(apiKey,query,page)

}