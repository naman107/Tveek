package com.example.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMjUxMjBhY2YxYjk1NzI0ZDE2MDU3YWJlZjliNDhjZCIsInN1YiI6IjVkZGNkM2JjNGY1ODAxMDAxNmY4MzhmZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6hY6co40MI0hbOC1l8AEeZN8wMaft2JtkeHTnf3mQV4"
        )
        return chain.proceed(request.build())
    }
}