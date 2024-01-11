package com.example.domain.usecases

import com.example.domain.models.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

class GetSearchedShowsUseCase(
    private val repository: ITvRepository
) {
    suspend operator fun invoke(showName: String): Flow<DataState<TvShow>?> = repository.getSearchedShows(showName)
}