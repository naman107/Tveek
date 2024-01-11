package com.example.domain.repository

import com.example.domain.models.TvShow
import kotlinx.coroutines.flow.Flow

interface IFavouriteTvRepository {
    suspend fun saveFavoriteShow(show: TvShow.Result)
    suspend fun getFavoriteShows(): Flow<List<TvShow.Result>>
    suspend fun deleteFavorite(id: Long)
}