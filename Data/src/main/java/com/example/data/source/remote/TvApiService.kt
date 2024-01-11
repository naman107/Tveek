package com.example.data.source.remote

import com.example.data.model.TvShowResponse
import com.example.data.model.TvShowSearchResponse
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
    ): Response<TvShowSearchResponse>

    @GET("tv/{id}/similar")
    suspend fun getSimilarShows(
        @Path("id") id: Long
    ): Response<TvShowResponse>

}

// https://image.tmdb.org/t/p/original/6U9CPeD8obHzweikFhiLhpc7YBT.jpg