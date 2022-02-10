package com.example.core.data.source.remote.response.detailmovie

import com.google.gson.annotations.SerializedName

data class ProductionCompanyResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("logo_path")
    val logoPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("origin_country")
    val originCountry: String,


)