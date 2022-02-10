package com.example.core.data.source.remote.response.detailmovie

import com.google.gson.annotations.SerializedName

data class ProductionCountriesResponse (

    @field:SerializedName("iso_3166_1")
    val iso_3166_1: String,

    @field:SerializedName("name")
    val name: String


    )