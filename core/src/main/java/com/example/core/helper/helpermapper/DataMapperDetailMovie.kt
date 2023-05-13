package com.example.core.helper.helpermapper

import com.example.core.data.source.local.entity.DetailMovieEntity
import com.example.core.data.source.remote.response.detailmovie.DetailMovieResponse
import com.example.core.domain.model.DetailMovie

object DataMapperDetailMovie {

    fun mapResponsetoEntities(input: List<DetailMovieResponse>): List<DetailMovieEntity> {
        val detailMovieList = ArrayList<DetailMovieEntity>()
        input.map {
            val detailMovie = DetailMovieEntity(
                adult = it.adult,
                backdropPath = it.backdropPath,
                belongsToCollectionId = it.belongsToCollection?.id,
                belongsToCollectionName = it.belongsToCollection?.name,
                belongsToCollectionPosterPath = it.belongsToCollection?.posterPath,
                belongsToCollectionBackdropPath = it.belongsToCollection?.backdropPath,
                budget = it.budget,
                genresId = it.genres?.get(0)?.id,
                genresName = it.genres?.get(0)?.name,
                homepage = it.homepage,
                id = it.id,
                imdbId = it.imdbId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                productionCompaniesId = it.productionCompanies?.get(0)?.id,
                productionCompaniesLogoPath = it.productionCompanies?.get(0)?.logoPath,
                productionCompaniesName = it.productionCompanies?.get(0)?.name,
                productionCompaniesOriginCountry = it.productionCompanies?.get(0)?.originCountry,
                productionCountriesIso_3166_1 = it.productionCountries?.get(0)?.iso_3166_1,
                productionCountriesName = it.productionCountries?.get(0)?.name,
                releaseDate = it.releaseDate,
                revenue = it.revenue,
                runtime = it.runtime,
                spokenLanguagesEnglishName = it.spokenLanguages?.get(0)?.englishName,
                spokenLanguagesIso_639_1 = it.spokenLanguages?.get(0)?.iso_639_1,
                spokenLanguagesName = it.spokenLanguages?.get(0)?.name,
                status = it.status,
                tagline = it.tagline,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
            detailMovieList.add(detailMovie)
        }

        return detailMovieList
    }

    fun mapEntitiestoDomain(input: List<DetailMovieEntity>): List<DetailMovie> =
        input.map {
            DetailMovie(
                adult = it.adult,
                backdropPath = it.backdropPath,
                belongsToCollectionId = it.belongsToCollectionId,
                belongsToCollectionName = it.belongsToCollectionName,
                belongsToCollectionPosterPath = it.belongsToCollectionPosterPath,
                belongsToCollectionBackdropPath = it.belongsToCollectionBackdropPath,
                budget = it.budget,
                genresId = it.genresId,
                genresName = it.genresName,
                homepage = it.homepage,
                id = it.id,
                imdbId = it.imdbId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                productionCompaniesId = it.productionCompaniesId,
                productionCompaniesLogoPath = it.productionCompaniesLogoPath,
                productionCompaniesName = it.productionCompaniesName,
                productionCompaniesOriginCountry = it.productionCompaniesOriginCountry,
                productionCountriesIso_3166_1 = it.productionCountriesIso_3166_1,
                productionCountriesName = it.productionCountriesName,
                releaseDate = it.releaseDate,
                revenue = it.revenue,
                runtime = it.runtime,
                spokenLanguagesEnglishName = it.spokenLanguagesEnglishName,
                spokenLanguagesIso_639_1 = it.spokenLanguagesIso_639_1,
                spokenLanguagesName = it.spokenLanguagesName,
                status = it.status,
                tagline = it.tagline,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
}