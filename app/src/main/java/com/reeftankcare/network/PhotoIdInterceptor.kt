package com.reeftankcare.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val API_KEY = "9440c03e843a60a0839fe0fa74ed25a0"

class PhotoIdInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequired: Request = chain.request()

        val newUrl: HttpUrl = originalRequired.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format","json")
            .addQueryParameter("nojsoncallback", "1")
            .addQueryParameter("safesearch","1")
            .build()

        val newRequest: Request = originalRequired.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}