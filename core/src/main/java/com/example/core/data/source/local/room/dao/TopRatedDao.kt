package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.TopRatedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedDao {

    @Query("SELECT * FROM top_rated")
    fun getTopRated(): Flow<List<TopRatedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertTopRated(topRated: List<TopRatedEntity>)

    @Query("DELETE FROM top_rated")
    suspend fun deleteTopRated()

    @Transaction
    suspend fun insertAndDeleteTopRated(topRated: List<TopRatedEntity>) {
        deleteTopRated()
        insertTopRated(topRated)
    }

}