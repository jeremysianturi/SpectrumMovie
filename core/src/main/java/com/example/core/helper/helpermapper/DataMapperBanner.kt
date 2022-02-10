package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.BannerEntity
import com.example.core.data.source.remote.response.banner.BannerResponse
import com.example.core.domain.model.Banner

object DataMapperBanner {

    fun mapResponsetoEntities(input: List<BannerResponse>): List<BannerEntity> {
        val bannerList = ArrayList<BannerEntity>()
        input.map {
            val banner = BannerEntity(
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
            bannerList.add(banner)
        }

        return bannerList
    }

    fun mapEntitiestoDomain(input: List<BannerEntity>): List<Banner> =
        input.map {
            Banner(
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