package com.medialink.keloladatasubmission.data.source.remote.retrofit

import com.medialink.keloladatasubmission.data.source.remote.response.movie.ListMovie
import com.medialink.keloladatasubmission.data.source.remote.response.movie.MovieDetail
import com.medialink.keloladatasubmission.data.source.remote.response.tv.ListTv
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("tv/popular")
    suspend fun getListTv(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ListTv

    @GET("tv/{id}")
    suspend fun getTvShow(
        @Path("id") id: Int,
        @Query("language") language: String
    ): TvDetail

    @GET("movie/popular")
    suspend fun getListMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ListMovie

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("language") language: String
    ): MovieDetail

}