package com.example.core.domain.usecase.detailmovie

import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import kotlinx.coroutines.flow.Flow

interface DetailMovieUsecase {

    fun getDetailMovie(movieId: String, apiKey: String): Flow<Resource<List<DetailMovie>>>

}