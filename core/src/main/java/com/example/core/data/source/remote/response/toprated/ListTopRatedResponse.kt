package com.example.core.data.source.remote.response.toprated

import com.google.gson.annotations.SerializedName

data class ListTopRatedResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<TopRatedResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)