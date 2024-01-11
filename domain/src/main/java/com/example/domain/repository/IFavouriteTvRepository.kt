package com.example.domain.repository

import com.example.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IFavouriteTvRepository {
    suspend fun saveFavoriteShows(shows: List<TvShow>): Flow<Boolean>
    suspend fun getFavoriteShows(): Flow<List<TvShow>>
}