package com.example.data.model

import com.example.domain.models.TvShow

data class TvShowResponse(
    val page: Int,
    val results: List<Result?>,
    val total_pages: Int,
    val total_results: Int
){
    data class Result(
        val id: Long,
        val backdrop_path: String?,
        val name: String,
        val overview: String,
        val poster_path: String?,
    )
}

fun TvShowResponse.toShow(): TvShow {
    val ls = ArrayList<TvShow.Result>()
    this.results.map {
        val obj = it?.let { it1 ->
            TvShow.Result(
                id = it1.id,
                name = it.name,
                overview = it.overview,
                poster_path = it.poster_path,
                isLiked = false
            )
        }
        if (obj != null) {
            ls.add(obj)
        }
    }
    return TvShow(
        results = ls
    )
}