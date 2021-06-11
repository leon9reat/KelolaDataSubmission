package com.medialink.keloladatasubmission.data.source.remote.retrofit

import com.medialink.keloladatasubmission.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}