package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.BannerEntity
import com.example.core.data.source.local.entity.PopularMoviesEntity
import com.example.core.data.source.remote.response.banner.BannerResponse
import com.example.core.data.source.remote.response.popularmovies.PopularMovieResponse
import com.example.core.domain.model.Banner
import com.example.core.domain.model.PopularMovies

object DataMapperPopularMovies {

    fun mapResponsetoEntities(input: List<PopularMovieResponse>): List<PopularMoviesEntity> {
        val popularMoviesList = ArrayList<PopularMoviesEntity>()
        input.map {
            val popularMovies = PopularMoviesEntity(
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
            popularMoviesList.add(popularMovies)
        }

        return popularMoviesList
    }

    fun mapEntitiestoDomain(input: List<PopularMoviesEntity>): List<PopularMovies> =
        input.map {
            PopularMovies(
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