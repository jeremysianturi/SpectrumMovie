package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.TopRatedEntity
import com.example.core.data.source.remote.response.toprated.TopRatedResponse
import com.example.core.domain.model.TopRated

object DataMapperTopRated {

    fun mapResponsetoEntities(input: List<TopRatedResponse>): List<TopRatedEntity> {
        val topRatedList = ArrayList<TopRatedEntity>()
        input.map {
            val topRated = TopRatedEntity(
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
            topRatedList.add(topRated)
        }

        return topRatedList
    }

    fun mapEntitiestoDomain(input: List<TopRatedEntity>): List<TopRated> =
        input.map {
            TopRated(
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