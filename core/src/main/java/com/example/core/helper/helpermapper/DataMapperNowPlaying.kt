package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.NowPlayingEntity
import com.example.core.data.source.remote.response.nowplaying.NowPlayingResponse
import com.example.core.domain.model.NowPlaying

object DataMapperNowPlaying {

    fun mapResponsetoEntities(input: List<NowPlayingResponse>): List<NowPlayingEntity> {
        val nowPlayingList = ArrayList<NowPlayingEntity>()
        input.map {
            val nowPlaying = NowPlayingEntity(
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
            nowPlayingList.add(nowPlaying)
        }

        return nowPlayingList
    }

    fun mapEntitiestoDomain(input: List<NowPlayingEntity>): List<NowPlaying> =
        input.map {
            NowPlaying(
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