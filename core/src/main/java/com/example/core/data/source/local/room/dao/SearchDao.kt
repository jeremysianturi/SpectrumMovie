package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.SearchEntity
import com.example.core.data.source.local.entity.UpcomingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Query("SELECT * FROM search")
    fun getSearch(): Flow<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: List<SearchEntity>)

    @Query("DELETE FROM search")
    suspend fun deleteSearch()

    @Transaction
    suspend fun insertAndDeleteSearch(search: List<SearchEntity>) {
        deleteSearch()
        insertSearch(search)
    }
}