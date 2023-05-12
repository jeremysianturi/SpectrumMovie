package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getGenre(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<GenreEntity>)

    @Query("DELETE FROM genre")
    suspend fun deleteGenre()

    @Transaction
    suspend fun insertAndDeleteGenre(genre: List<GenreEntity>) {
        deleteGenre()
        insertGenre(genre)
    }

}