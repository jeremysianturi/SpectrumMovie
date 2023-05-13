package com.example.core.domain.usecase.search

import com.example.core.data.Resource
import com.example.core.domain.model.Search
import com.example.core.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow

interface SearchUsecase {

    fun getSearch(apiKey: String, query: String, page: String): Flow<Resource<List<Search>>>

}