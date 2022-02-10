package com.example.core.data.source.remote.response.detailmovie

import com.google.gson.annotations.SerializedName

data class GenresResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String

    )