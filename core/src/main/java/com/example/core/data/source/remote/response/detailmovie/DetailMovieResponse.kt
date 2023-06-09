package com.example.core.data.source.remote.response.detailmovie

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse (

    @field:SerializedName("adult")
    val adult: Boolean?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionResponse?,

    @field:SerializedName("budget")
    val budget: Int?,

    @field:SerializedName("genres")
    val genres: List<GenresResponse>?,

    @field:SerializedName("homepage")
    val homepage: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("imdb_id")
    val imdbId: String?,

    @field:SerializedName("original_language")
    val originalLanguage: String?,

    @field:SerializedName("original_title")
    val originalTitle: String?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("popularity")
    val popularity: Double?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>?,

    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesResponse>?,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("revenue")
    val revenue: Long?,

    @field:SerializedName("runtime")
    val runtime: Int?,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>?,

    @field:SerializedName("status")
    val status: String?,

    @field:SerializedName("tagline")
    val tagline: String?,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("video")
    val video: Boolean?,

    @field:SerializedName("vote_average")
    val voteAverage: Double?,

    @field:SerializedName("vote_count")
    val voteCount: Int?,



    )