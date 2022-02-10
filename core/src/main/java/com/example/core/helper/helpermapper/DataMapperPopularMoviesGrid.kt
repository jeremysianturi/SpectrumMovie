package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.PopularMoviesGridEntity
import com.example.core.data.source.remote.response.popularmoviesgrid.PopularMovieGridResponse
import com.example.core.domain.model.PopularMoviesGrid

object DataMapperPopularMoviesGrid {

    fun mapResponsetoEntities(input: List<PopularMovieGridResponse>): List<PopularMoviesGridEntity> {
        val popularMoviesGridList = ArrayList<PopularMoviesGridEntity>()
        input.map {
            val popularMoviesGrid = PopularMoviesGridEntity(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds.toString(),
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
            popularMoviesGridList.add(popularMoviesGrid)
        }

        return popularMoviesGridList
    }

    fun mapEntitiestoDomain(input: List<PopularMoviesGridEntity>): List<PopularMoviesGrid> =
        input.map {
            PopularMoviesGrid(
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                id = it.id,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
}