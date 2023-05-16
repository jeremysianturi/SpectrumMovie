package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SubmitResponse(

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean
)