package com.example.core.utils

import android.content.Context
import androidx.core.content.edit

class UserPreference(context: Context) {

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val LAT = "lat"
        private const val LONG = "long"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setPref(value: PreferenceEntity) {
        preferences.edit {
            putString(LAT, value.lat)
            putString(LONG, value.long)
        }
    }

    fun getPref(): PreferenceEntity {

        val data = PreferenceEntity()
        data.lat = preferences.getString(LAT, "")
        data.long = preferences.getString(LONG, "").toString()

        return data
    }
}