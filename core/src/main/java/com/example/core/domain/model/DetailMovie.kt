package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovie(
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollectionId: Int?,
    val belongsToCollectionName: String?,
    val belongsToCollectionPosterPath: String?,
    val belongsToCollectionBackdropPath: String?,
    val budget: Int?,
    val genresId: Int?,
    val genresName: String?,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompaniesId: Int?,
    val productionCompaniesLogoPath: String?,
    val productionCompaniesName: String?,
    val productionCompaniesOriginCountry: String?,
    val productionCountriesIso_3166_1: String?,
    val productionCountriesName: String?,
    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spokenLanguagesEnglishName: String?,
    val spokenLanguagesIso_639_1: String?,
    val spokenLanguagesName: String?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
) : Parcelable