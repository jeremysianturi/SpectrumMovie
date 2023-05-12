package com.example.core.data.source.remote.response.upcoming

import com.google.gson.annotations.SerializedName

data class ListUpcomingResponse (
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<UpcomingResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int
        )