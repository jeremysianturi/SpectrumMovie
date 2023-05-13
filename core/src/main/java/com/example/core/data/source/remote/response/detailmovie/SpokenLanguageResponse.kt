package com.example.core.data.source.remote.response.detailmovie

import com.google.gson.annotations.SerializedName

data class SpokenLanguageResponse (

    @field:SerializedName("english_name")
    val englishName: String?,

    @field:SerializedName("iso_639_1")
    val iso_639_1: String?,

    @field:SerializedName("name")
    val name: String?


)