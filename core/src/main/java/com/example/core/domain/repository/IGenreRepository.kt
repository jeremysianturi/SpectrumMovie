package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Genre
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow

interface IGenreRepository {

    fun getGenre(apiKey: String) : Flow<Resource<List<Genre>>>

    fun getSearchGenre(genreIds: List<Int>): Flow<List<Genre>>

}