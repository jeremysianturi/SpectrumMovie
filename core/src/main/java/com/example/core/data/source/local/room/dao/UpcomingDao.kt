package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.UpcomingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingDao {

    @Query("SELECT * FROM upcoming")
    fun getUpcoming(): Flow<List<UpcomingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcoming(upcoming: List<UpcomingEntity>)

    @Query("DELETE FROM upcoming")
    suspend fun deleteUpcoming()

    @Transaction
    suspend fun insertAndDeleteUpcoming(upcoming: List<UpcomingEntity>) {
        deleteUpcoming()
        insertUpcoming(upcoming)
    }

}