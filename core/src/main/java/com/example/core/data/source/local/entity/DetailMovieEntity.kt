package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "detail_movie")
data class DetailMovieEntity(

    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "belongs_to_collection_id")
    val belongsToCollectionId: Int?,

    @ColumnInfo(name = "belongs_to_collection_name")
    val belongsToCollectionName: String?,

    @ColumnInfo(name = "belongs_to_collection_posterpath")
    val belongsToCollectionPosterPath: String?,

    @ColumnInfo(name = "belongs_to_collection_backdroppath")
    val belongsToCollectionBackdropPath: String?,

    @ColumnInfo(name = "budget")
    val budget: Int?,

    @ColumnInfo(name = "genres_id")
    val genresId: Int?,

    @ColumnInfo(name = "genres_name")
    val genresName: String?,

    @ColumnInfo(name = "homepage")
    val homepage: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String?,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "production_companies_id")
    val productionCompaniesId: Int?,

    @ColumnInfo(name = "production_companies_logopath")
    val productionCompaniesLogoPath: String?,

    @ColumnInfo(name = "production_companies_name")
    val productionCompaniesName: String?,

    @ColumnInfo(name = "production_companies_origin_country")
    val productionCompaniesOriginCountry: String?,

    @ColumnInfo(name = "production_countries_iso_3166_1")
    val productionCountriesIso_3166_1: String?,

    @ColumnInfo(name = "production_countries_name")
    val productionCountriesName: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "revenue")
    val revenue: Long?,

    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @ColumnInfo(name = "spoken_languages_english_name")
    val spokenLanguagesEnglishName: String?,

    @ColumnInfo(name = "spoken_languages_iso_639_1")
    val spokenLanguagesIso_639_1: String?,

    @ColumnInfo(name = "spoken_languages_name")
    val spokenLanguagesName: String?,

    @ColumnInfo(name = "status")
    val status: String?,

    @ColumnInfo(name = "tagline")
    val tagline: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "video")
    val video: Boolean?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    )