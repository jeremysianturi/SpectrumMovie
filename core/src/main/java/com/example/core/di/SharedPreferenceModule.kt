package com.example.core.di

import android.content.Context
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class SharedPreferenceModule {

    @Singleton
    @Provides
    fun preference (@ApplicationContext context: Context) : PreferenceEntity {
        return UserPreference(context).getPref()
    }
}