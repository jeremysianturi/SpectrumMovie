package com.example.core.domain.usecase.popularmovies

import com.example.core.data.Resource
import com.example.core.domain.model.PopularMovies
import kotlinx.coroutines.flow.Flow

interface PopularMoviesUsecase {

    fun getPopularMovies(apiKey: String,page: String): Flow<Resource<List<PopularMovies>>>

}