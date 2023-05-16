package com.example.core.data.source.remote.post

import com.google.gson.annotations.SerializedName

data class LovedPost(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster")
    val poster: Int,

    @field:SerializedName("title")
    val title: String,

)