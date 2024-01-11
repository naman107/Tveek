package com.example.data.repository

import com.example.data.source.remote.TvApiService
import com.example.data.model.toShow
import com.example.domain.model.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TvRepositoryImpl(
    private val apiService: TvApiService
) : ITvRepository {

    override suspend fun getTrendingShows(): Flow<DataState<TvShow>?> = flow {
        val result = apiService.getTrendingShows()
        if (!result.isSuccessful || result.errorBody() != null || result.code() != 200) {
            emit(DataState.Error("Couldn't load trending shows!"))
        }
        emit(result.body()?.let { DataState.Success(it.toShow()) })
    }

    override suspend fun getSearchedShows(showName: String): Flow<DataState<TvShow>?> = flow {
        val result = apiService.getSearchedShows(showName)
        if (!result.isSuccessful || result.errorBody() != null || result.code() != 200) {
            emit(DataState.Error("Couldn't load searched shows!"))
        }
        emit(result.body()?.let { DataState.Success(it.toShow()) })
    }

    override suspend fun getSimilarShows(id: Long): Flow<DataState<TvShow>?> = flow {
        val result = apiService.getSimilarShows(id)
        if (!result.isSuccessful || result.errorBody() != null || result.code() != 200) {
            emit(DataState.Error("Couldn't load similar shows!"))
        }
        emit(result.body()?.let { DataState.Success(it.toShow()) })
    }

}
