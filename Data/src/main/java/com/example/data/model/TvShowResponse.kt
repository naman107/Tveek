package com.example.data.model

import com.example.domain.model.TvShow

data class TvShowResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
){
    data class Result(
        val id: Long,
        val backdrop_path: String,
        val name: String,
        val overview: String,
        val poster_path: String,
    )
}

fun TvShowResponse.toShow(): TvShow {
    val ls = ArrayList<TvShow.Result>()
    this.results.map {
        val obj = TvShow.Result(
            id = it.id,
            backdrop_path = it.backdrop_path,
            name = it.name,
            overview = it.overview,
            poster_path = it.poster_path
        )
        ls.add(obj)
    }
    return TvShow(
        results = ls
    )
}