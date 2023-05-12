package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.UpcomingEntity
import com.example.core.data.source.remote.response.upcoming.UpcomingResponse
import com.example.core.domain.model.Upcoming

object DataMapperUpcoming {

    fun mapResponsetoEntities(input: List<UpcomingResponse>): List<UpcomingEntity> {
        val upcomingList = ArrayList<UpcomingEntity>()
        input.map {
            val upcoming = UpcomingEntity(
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
            upcomingList.add(upcoming)
        }

        return upcomingList
    }

    fun mapEntitiestoDomain(input: List<UpcomingEntity>): List<Upcoming> =
        input.map {
            Upcoming(
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