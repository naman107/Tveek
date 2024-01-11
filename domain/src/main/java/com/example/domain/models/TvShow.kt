package com.example.domain.models

data class TvShow(
    val results: List<Result>
){
    data class Result(
        val id: Long,
        val name: String = "",
        val overview: String = "",
        val poster_path: String? = "",
        var isLiked: Boolean
    )
}
