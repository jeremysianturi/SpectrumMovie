package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import kotlinx.coroutines.flow.Flow

interface IDetailMovieRepository {

    fun getDetailMovie(movieId: String, apiKey: String) : Flow<Resource<List<DetailMovie>>>

}