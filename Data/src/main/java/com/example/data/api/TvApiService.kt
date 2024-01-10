package com.example.data.api

import com.example.data.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {

    @GET("trending/tv/week")
    suspend fun getTrendingShows(): Response<TvShowResponse>

    @GET("search/movie")
    suspend fun getSearchedShows(
        @Query("query") showName: String
    ): Response<TvShowResponse>

    @GET("tv/{id}/similar")
    suspend fun getSimilarShows(
        @Path("id") id: Long
    ): Response<TvShowResponse>

}

// https://image.tmdb.org/t/p/original/6U9CPeD8obHzweikFhiLhpc7YBT.jpg