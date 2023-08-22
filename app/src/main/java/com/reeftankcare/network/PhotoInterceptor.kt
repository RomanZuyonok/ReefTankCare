package com.reeftankcare.network

import com.reeftankcare.utilits.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class PhotoInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequired: Request = chain.request()

        val newUrl: HttpUrl = originalRequired.url.newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .addQueryParameter("format","json")
            .addQueryParameter("nojsoncallback", "1")
            .addQueryParameter("extras","url_s")
            .addQueryParameter("safesearch","1")
            .build()

        val newRequest: Request = originalRequired.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}