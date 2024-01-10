package com.example.domain.model

data class TvShow(
    val results: List<Result>
){
    data class Result(
        val id: Long,
        val name: String,
        val overview: String,
        val poster_path: String?,
        val isLiked: Boolean = false
    )
}
