package com.example.core.data.source.remote.response.banner

import com.google.gson.annotations.SerializedName

data class ListBannerResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<BannerResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)