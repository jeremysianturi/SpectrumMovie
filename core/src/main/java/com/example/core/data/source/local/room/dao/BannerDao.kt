package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.BannerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {

    @Query("SELECT * FROM banner")
    fun getBanner(): Flow<List<BannerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanner(banner: List<BannerEntity>)

    @Query("DELETE FROM banner")
    suspend fun deleteBanner()

    @Transaction
    suspend fun insertAndDeleteBanner(banner: List<BannerEntity>) {
        deleteBanner()
        insertBanner(banner)
    }

}