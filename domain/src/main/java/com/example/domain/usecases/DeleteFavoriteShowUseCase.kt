package com.example.domain.usecases

import com.example.domain.repository.IFavouriteTvRepository

class DeleteFavoriteShowUseCase(
    private val repository: IFavouriteTvRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteFavorite(id)
}