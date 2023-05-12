package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.NowPlayingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingDao {

    @Query("SELECT * FROM now_playing")
    fun getNowPlaying(): Flow<List<NowPlayingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>)

    @Query("DELETE FROM now_playing")
    suspend fun deleteNowPlaying()

    @Transaction
    suspend fun insertAndDeleteNowPlaying(nowPlaying: List<NowPlayingEntity>) {
        deleteNowPlaying()
        insertNowPlaying(nowPlaying)
    }

}