package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.SearchEntity
import com.example.core.data.source.local.entity.UpcomingEntity
import com.example.core.data.source.remote.response.search.SearchResponse
import com.example.core.data.source.remote.response.upcoming.UpcomingResponse
import com.example.core.domain.model.Search
import com.example.core.domain.model.Upcoming

object DataMapperSearch {

    fun mapResponsetoEntities(input: List<SearchResponse>): List<SearchEntity> {
        val searchList = ArrayList<SearchEntity>()
        input.map {
            val search = SearchEntity(
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
            searchList.add(search)
        }

        return searchList
    }

    fun mapEntitiestoDomain(input: List<SearchEntity>): List<Search> =
        input.map {
            Search(
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