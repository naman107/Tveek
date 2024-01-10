package com.example.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(
            AUTHROIZATION,
            AUTH_TOKEN
        )
        return chain.proceed(request.build())
    }
}