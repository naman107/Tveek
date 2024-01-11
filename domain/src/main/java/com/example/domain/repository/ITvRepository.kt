package com.example.domain.repository

import com.example.domain.model.TvShow
import com.example.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ITvRepository {
    suspend fun getTrendingShows(): Flow<DataState<TvShow>?>
    suspend fun getSearchedShows(showName: String): Flow<DataState<TvShow>?>
    suspend fun getSimilarShows(id: Long): Flow<DataState<TvShow>?>
}