package com.example.data.utils

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val error: String) : Response<Nothing>()
}
