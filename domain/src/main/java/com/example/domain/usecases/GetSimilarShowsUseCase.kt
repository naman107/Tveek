package com.example.domain.usecases

import com.example.domain.models.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

class GetSimilarShowsUseCase(
    private val repository: ITvRepository
) {
    suspend operator fun invoke(id: Long): Flow<DataState<TvShow>?> = repository.getSimilarShows(id)
}