package com.example.core.data.source.remote.response.popularmovies

import com.google.gson.annotations.SerializedName

data class ListPopularMoviesResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<PopularMovieResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)