package com.example.tveek.di

import com.example.data.api.TvApiService
import com.example.data.repository.TvRepositoryImpl
import com.example.domain.repository.ITvRepository
import com.example.domain.usecases.GetSearchedShowsUseCase
import com.example.domain.usecases.GetSimilarShowsUseCase
import com.example.domain.usecases.GetTrendingShowsUseCase
import com.example.tveek.viewmodels.TvShowsViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    factory { providesTvRepository(get()) }
    factory { providesGetTrendingShowsUseCase(get()) }
    factory { providesGetSearchedShowsUseCase(get()) }
    factory { providesGetSimilarShowsUseCase(get()) }
    viewModel { TvShowsViewModels(get(),get(),get()) }
}

fun providesTvRepository(apiService: TvApiService): ITvRepository  {
    return TvRepositoryImpl(apiService)
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

