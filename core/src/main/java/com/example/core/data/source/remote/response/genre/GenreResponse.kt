package com.example.core.data.source.remote.response.genre

import com.google.gson.annotations.SerializedName

data class GenreResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

)