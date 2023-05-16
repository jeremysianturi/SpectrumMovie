package com.example.core.domain.usecase.genre

import com.example.core.data.Resource
import com.example.core.data.repository.GenreRepository
import com.example.core.domain.model.Genre
import com.example.core.domain.model.PopularMoviesGrid
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreInteractor @Inject constructor(private val genreRepository: GenreRepository) :
    GenreUsecase {

    override fun getGenre(apiKey: String): Flow<Resource<List<Genre>>> =
        genreRepository.getGenre(apiKey)

    override fun getSearchGenreIds(genreIds: List<Int>): Flow<List<Genre>> =
        genreRepository.getSearchGenre(genreIds)

}