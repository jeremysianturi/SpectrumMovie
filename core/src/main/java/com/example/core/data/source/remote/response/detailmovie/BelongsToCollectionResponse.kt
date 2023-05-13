package com.example.core.data.source.remote.response.detailmovie

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionResponse (

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,


    )