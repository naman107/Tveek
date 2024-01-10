package com.example.data.model

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