package com.example.tveek.di

import com.example.data.api.TvApiService
import com.example.data.repository.TvRepositoryImpl
import com.example.domain.repository.ITvRepository
import org.koin.dsl.module

val applicationModule = module {
    factory { providesTvRepository(get()) }
}

fun providesTvRepository(apiService: TvApiService): ITvRepository  {
    return TvRepositoryImpl(apiService)
}