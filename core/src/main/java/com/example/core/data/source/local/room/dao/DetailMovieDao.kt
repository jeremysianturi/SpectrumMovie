package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.DetailMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailMovieDao {

    @Query("SELECT * FROM detail_movie")
    fun getDetailMovie(): Flow<List<DetailMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovie: List<DetailMovieEntity>)

    @Query("DELETE FROM detail_movie")
    suspend fun deleteDetailMovie()

    @Transaction
    suspend fun insertAndDeleteDetailMovie(detailMovie: List<DetailMovieEntity>) {
        deleteDetailMovie()
        insertDetailMovie(detailMovie)
    }

}