package com.example.tveek.di

import android.content.Context
import com.example.data.repository.FavouriteTvRepositoryImpl
import com.example.data.repository.TvRepositoryImpl
import com.example.data.source.remote.TvApiService
import com.example.domain.repository.IFavouriteTvRepository
import com.example.domain.repository.ITvRepository
import com.example.domain.usecases.DeleteFavoriteShowUseCase
import com.example.domain.usecases.GetFavouriteShowsUseCase
import com.example.domain.usecases.GetSearchedShowsUseCase
import com.example.domain.usecases.GetSimilarShowsUseCase
import com.example.domain.usecases.GetTrendingShowsUseCase
import com.example.domain.usecases.SaveFavouriteShowsUseCase
import com.example.tveek.viewmodels.FavouriteTvShowViewModel
import com.example.tveek.viewmodels.TvShowsViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    factory { providesTvRepository(get()) }
    factory { providesGetTrendingShowsUseCase(get()) }
    factory { providesGetSearchedShowsUseCase(get()) }
    factory { providesGetSimilarShowsUseCase(get()) }
    factory { providesSaveFavouriteShowsUseCase(get()) }
    factory { providesGetFavouriteShowsUseCase(get()) }
    factory { providesDeleteFavoriteShowUseCase(get()) }
    factory { providesFavouriteTvShowsRepository(androidContext()) }
    viewModel { TvShowsViewModels(get(),get(),get()) }
    viewModel { FavouriteTvShowViewModel(get(),get(),get()) }
}

fun providesTvRepository(apiService: TvApiService): ITvRepository  {
    return TvRepositoryImpl(apiService)
}

fun providesFavouriteTvShowsRepository(context: Context): IFavouriteTvRepository  {
    return FavouriteTvRepositoryImpl(context)
}

fun providesGetTrendingShowsUseCase(repository: ITvRepository): GetTrendingShowsUseCase{
    return GetTrendingShowsUseCase(repository)
}

fun providesGetSearchedShowsUseCase(repository: ITvRepository): GetSearchedShowsUseCase {
    return GetSearchedShowsUseCase(repository)
}

fun providesGetSimilarShowsUseCase(repository: ITvRepository): GetSimilarShowsUseCase{
    return GetSimilarShowsUseCase(repository)
}

fun providesSaveFavouriteShowsUseCase(repository: IFavouriteTvRepository): SaveFavouriteShowsUseCase {
    return SaveFavouriteShowsUseCase(repository)
}

fun providesGetFavouriteShowsUseCase(repository: IFavouriteTvRepository): GetFavouriteShowsUseCase {
    return GetFavouriteShowsUseCase(repository)
}

fun providesDeleteFavoriteShowUseCase(repository: IFavouriteTvRepository): DeleteFavoriteShowUseCase{
    return DeleteFavoriteShowUseCase(repository)
}


