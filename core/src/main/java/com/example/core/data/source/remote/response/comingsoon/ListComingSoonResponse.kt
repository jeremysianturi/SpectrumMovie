package com.example.core.data.source.remote.response.comingsoon

import com.example.core.data.source.remote.response.popularmovies.PopularMovieResponse
import com.google.gson.annotations.SerializedName

data class ListComingSoonResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<ComingSoonResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)