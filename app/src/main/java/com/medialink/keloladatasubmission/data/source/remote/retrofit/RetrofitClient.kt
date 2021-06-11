package com.medialink.keloladatasubmission.data.source.remote.retrofit

import com.medialink.keloladatasubmission.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getApiService(): ApiService {
            val authInterceptor = AuthInterceptor()

            val clientBuild = OkHttpClient.Builder().addInterceptor(authInterceptor)

            if (BuildConfig.DEBUG)  {
                val loggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                clientBuild.addInterceptor(loggingInterceptor)
            }
            val client = clientBuild.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}