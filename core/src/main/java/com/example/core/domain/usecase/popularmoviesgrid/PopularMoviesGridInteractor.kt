package com.example.core.domain.usecase.popularmoviesgrid

import com.example.core.data.Resource
import com.example.core.data.repository.PopularMoviesGridRepository
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesGridInteractor @Inject constructor(private val popularMoviesGridRepository: PopularMoviesGridRepository) :
    PopularMoviesGridUsecase {

    override fun getPopularMoviesGrid(apiKey: String,page: String): Flow<Resource<List<PopularMoviesGrid>>> =
        popularMoviesGridRepository.getPopularMoviesGrid(apiKey,page)


    override fun getSearchPopularMoviesGrid(search: String): Flow<List<PopularMoviesGrid>> =
        popularMoviesGridRepository.getSearchPopularMoviesGrid(search)
}