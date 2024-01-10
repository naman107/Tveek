package com.example.domain.usecases

import com.example.domain.model.TvShow
import com.example.domain.repository.ITvRepository
import com.example.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

class SaveFavouriteShowsUseCase(
    private val repository: ITvRepository
) {
    suspend operator fun invoke(tvShow: TvShow): Flow<DataState<TvShow>?> = repository.saveFavoriteShows(tvShow)
}