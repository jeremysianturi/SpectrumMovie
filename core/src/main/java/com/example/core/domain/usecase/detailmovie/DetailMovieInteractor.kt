package com.example.core.domain.usecase.detailmovie

import com.example.core.data.Resource
import com.example.core.data.repository.DetailMovieRepository
import com.example.core.domain.model.DetailMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieInteractor @Inject constructor(private val detailMovieRepository: DetailMovieRepository) :
    DetailMovieUsecase {

    override fun getDetailMovie(movieId: String, apiKey: String): Flow<Resource<List<DetailMovie>>> =
        detailMovieRepository.getDetailMovie(movieId, apiKey)

}