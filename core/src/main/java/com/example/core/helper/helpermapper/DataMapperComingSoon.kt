package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.ComingSoonEntity
import com.example.core.data.source.remote.response.comingsoon.ComingSoonResponse
import com.example.core.domain.model.ComingSoon

object DataMapperComingSoon {

    fun mapResponsetoEntities(input: List<ComingSoonResponse>): List<ComingSoonEntity> {
        val comingSoonList = ArrayList<ComingSoonEntity>()
        input.map {
            val comingSoon = ComingSoonEntity(
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
            comingSoonList.add(comingSoon)
        }

        return comingSoonList
    }

    fun mapEntitiestoDomain(input: List<ComingSoonEntity>): List<ComingSoon> =
        input.map {
            ComingSoon(
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