package com.example.core.domain.usecase.genre

import com.example.core.data.Resource
import com.example.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreUsecase {
    fun getGenre(apiKey: String): Flow<Resource<List<Genre>>>
}