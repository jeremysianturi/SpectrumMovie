package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow

interface IPopularMoviesGridRepository {

    fun getPopularMoviesGrid(apiKey: String,page: String) : Flow<Resource<List<PopularMoviesGrid>>>

    fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGrid>>
}