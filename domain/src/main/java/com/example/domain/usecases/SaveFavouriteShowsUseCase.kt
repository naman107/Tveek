package com.example.domain.usecases

import com.example.domain.models.TvShow
import com.example.domain.repository.IFavouriteTvRepository

class SaveFavouriteShowsUseCase(
    private val repository: IFavouriteTvRepository
) {
    suspend operator fun invoke(show: TvShow.Result) = repository.saveFavoriteShow(show)
}