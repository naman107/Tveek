package com.example.data.repository

import com.example.data.api.TvApiService
import com.example.data.model.toShow
import com.example.domain.model.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState

class TvRepositoryImpl(
    private val apiService: TvApiService
): ITvRepository {
    override suspend fun getTrendingShows(): DataState<TvShow> {
        val result = apiService.getTrendingShows()
        if(!result.isSuccessful || result.errorBody() != null || result.code() != 200){
            return DataState.Error("Couldn't load trending shows!")
        }
        return DataState.Success(result.body()!!.toShow()) //check
    }

    override suspend fun getSearchedShows(showName: String): DataState<TvShow> {
        val result = apiService.getSearchedShows(showName)
        if(!result.isSuccessful || result.errorBody() != null || result.code() != 200){
            return DataState.Error("Couldn't load searched shows!")
        }
        return DataState.Success(result.body()!!.toShow())
    }

    override suspend fun getSimilarShows(id: Long): DataState<TvShow> {
        val result = apiService.getSimilarShows(id)
        if(!result.isSuccessful || result.errorBody() != null || result.code() != 200){
            return DataState.Error("Couldn't load similar shows!")
        }
        return DataState.Success(result.body()!!.toShow())
    }

}
