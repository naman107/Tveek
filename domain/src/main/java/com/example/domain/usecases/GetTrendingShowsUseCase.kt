package com.example.domain.usecases

import com.example.domain.model.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

class GetTrendingShowsUseCase(
    private val repository: ITvRepository
) {
    suspend operator fun invoke(): Flow<DataState<TvShow>?> = repository.getTrendingShows()
}