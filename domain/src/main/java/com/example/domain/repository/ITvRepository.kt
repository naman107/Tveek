package com.example.domain.repository

import com.example.domain.model.TvShow
import com.example.domain.utils.DataState

interface ITvRepository {
    suspend fun getTrendingShows(): DataState<TvShow>
    suspend fun getSearchedShows(showName: String): DataState<TvShow>
    suspend fun getSimilarShows(id: Long): DataState<TvShow>
}