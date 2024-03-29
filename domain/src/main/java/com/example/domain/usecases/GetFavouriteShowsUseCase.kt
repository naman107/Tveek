package com.example.domain.usecases

import com.example.domain.repository.IFavouriteTvRepository

class GetFavouriteShowsUseCase(
    private val repository: IFavouriteTvRepository
) {
    suspend operator fun invoke() = repository.getFavoriteShows()
}