package com.example.core.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreferenceEntity(
    var lat : String? = "",
    var long : String? = "",
) : Parcelable
