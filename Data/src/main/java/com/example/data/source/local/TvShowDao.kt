package com.example.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteTvShow(data: TvShowEntity)

    @Query("SELECT * FROM TV_SHOW")
    fun getFavouriteTvShows(): Flow<List<TvShowEntity>>

    @Query("DELETE FROM TV_SHOW WHERE id = :id")
    suspend fun deleteFavouriteTvShows(id: Long)

}