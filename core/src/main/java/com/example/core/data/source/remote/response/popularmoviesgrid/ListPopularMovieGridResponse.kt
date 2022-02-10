package com.example.core.data.source.remote.response.popularmoviesgrid

import com.example.core.data.source.remote.response.popularmovies.PopularMovieResponse
import com.google.gson.annotations.SerializedName

data class ListPopularMovieGridResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<PopularMovieGridResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)