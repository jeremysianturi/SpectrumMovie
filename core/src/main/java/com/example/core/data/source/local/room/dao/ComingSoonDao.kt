package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.ComingSoonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComingSoonDao {

    @Query("SELECT * FROM coming_soon")
    fun getComingSoon(): Flow<List<ComingSoonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComingSoon(comingSoon: List<ComingSoonEntity>)

    @Query("DELETE FROM coming_soon")
    suspend fun deleteComingSoon()

    @Transaction
    suspend fun insertAndDeleteComingSoon(comingSoon: List<ComingSoonEntity>) {
        deleteComingSoon()
        insertComingSoon(comingSoon)
    }

}