package com.example.core.domain.usecase.popularmovies

import com.example.core.data.Resource
import com.example.core.data.repository.PopularMoviesRepository
import com.example.core.domain.model.PopularMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesInteractor @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) :
    PopularMoviesUsecase {

    override fun getPopularMovies(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String): Flow<Resource<List<PopularMovies>>> =
        popularMoviesRepository.getPopularMovies(apiKey, language, sortBy, includeAdult, includeVideo, page)

}