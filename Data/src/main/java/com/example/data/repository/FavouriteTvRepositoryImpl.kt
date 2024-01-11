package com.example.data.repository

import com.example.domain.model.TvShow
import com.example.domain.repository.IFavouriteTvRepository
import kotlinx.coroutines.flow.Flow

class FavouriteTvRepositoryImpl(
): IFavouriteTvRepository {
    override suspend fun saveFavoriteShows(shows: List<TvShow>): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteShows(): Flow<List<TvShow>> {
        TODO("Not yet implemented")
    }

}