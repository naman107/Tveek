package com.example.tveek.di

import com.example.data.api.TvApiService
import com.example.data.repository.TvRepositoryImpl
import com.example.domain.repository.ITvRepository
import com.example.tveek.viewmodels.TvShowsViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    factory { providesTvRepository(get()) }
    viewModel { TvShowsViewModels(get()) }
}

fun providesTvRepository(apiService: TvApiService): ITvRepository  {
    return TvRepositoryImpl(apiService)
}

/*
fun providesTvShowsViewModel(repository: ITvRepository): TvShowsViewModels {
    return TvShowsViewModels(repository)
}*/
