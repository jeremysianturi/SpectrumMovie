package com.example.core.data.source.remote.response.search

import com.google.gson.annotations.SerializedName

data class ListSearchResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<SearchResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)