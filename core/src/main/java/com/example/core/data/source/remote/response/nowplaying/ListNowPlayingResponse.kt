package com.example.core.data.source.remote.response.nowplaying

import com.google.gson.annotations.SerializedName

data class ListNowPlayingResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<NowPlayingResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int
)