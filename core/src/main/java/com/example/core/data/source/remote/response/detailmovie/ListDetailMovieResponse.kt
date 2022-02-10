package com.example.core.data.source.remote.response.detailmovie

import com.example.core.data.source.remote.response.banner.BannerResponse
import com.google.gson.annotations.SerializedName

data class ListDetailMovieResponse(

    @field:SerializedName("")
    val data: DetailMovieResponse
)