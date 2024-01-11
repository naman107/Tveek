package com.example.data.repository

import android.content.Context
import com.example.data.model.entity.TvShowEntity
import com.example.data.source.local.AppDatabase
import com.example.domain.models.TvShow
import com.example.domain.repository.IFavouriteTvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouriteTvRepositoryImpl(
    private val context: Context
): IFavouriteTvRepository {
    override suspend fun saveFavoriteShow(show: TvShow.Result){
        val tvShowEntity = TvShowEntity(
            id = show.id,
            isLiked = show.isLiked
        )
        AppDatabase.getInstance(context).tvShowDao().insertFavouriteTvShow(tvShowEntity)
    }

    override suspend fun getFavoriteShows(): Flow<List<TvShow.Result>> = flow {
        val data = AppDatabase.getInstance(context).tvShowDao().getFavouriteTvShows()
        val ls = ArrayList<TvShow.Result>()
        data.collect { shows ->
            shows.map { show ->
                val obj = TvShow.Result(
                    id = show.id,
                    isLiked = show.isLiked
                )
                ls.add(obj)
            }
            emit(ls)
        }
    }

    override suspend fun deleteFavorite(id: Long) {
        AppDatabase.getInstance(context).tvShowDao().deleteFavouriteTvShows(id)
    }

}