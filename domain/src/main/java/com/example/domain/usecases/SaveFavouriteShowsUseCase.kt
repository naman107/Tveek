package com.example.domain.usecases

import com.example.domain.model.TvShow
import com.example.domain.repository.ITvRepository
import kotlinx.coroutines.flow.Flow

class SaveFavouriteShowsUseCase(
    private val repository: ITvRepository
) {
//    suspend operator fun invoke(tvShows: List<TvShow>): Flow<Boolean> = repository.saveFavoriteShows(tvShows)
}