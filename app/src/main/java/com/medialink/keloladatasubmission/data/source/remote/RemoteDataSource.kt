package com.medialink.keloladatasubmission.data.source.remote

import com.google.gson.Gson
import com.medialink.keloladatasubmission.data.source.remote.response.ApiResponse
import com.medialink.keloladatasubmission.data.source.remote.response.error.ErrorRespon
import com.medialink.keloladatasubmission.data.source.remote.response.movie.Movie
import com.medialink.keloladatasubmission.data.source.remote.response.movie.MovieDetail
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvDetail
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvShow
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiConfig
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiService
import retrofit2.HttpException

class RemoteDataSource private constructor(private val apiService: ApiService) {

    suspend fun getListMovies(page: Int): ApiResponse<List<Movie>?> {

        try {
            val listMovie = apiService.getListMovies(ApiConfig.LANGUAGE, page)
            return if (listMovie.results != null)
                ApiResponse.success(listMovie.results) else
                ApiResponse.empty("tidak ada data", null)

        } catch (throwable: Throwable) {
            return when (throwable) {
                is HttpException -> {
                    var err: ErrorRespon
                    throwable.response()?.errorBody()?.string().toString().let {
                        err = Gson().fromJson(it, ErrorRespon::class.java)
                    }
                    ApiResponse.error(err.statusMessage ?: "error", null)
                }
                else -> {
                    ApiResponse.error(throwable.message ?: "error", null)
                }

            }
        }
    }

    suspend fun getMovie(id: Int): ApiResponse<MovieDetail?> {
        try {
            return ApiResponse.success(apiService.getMovie(id, ApiConfig.LANGUAGE))
        } catch (throwable: Throwable) {
            return when (throwable) {
                is HttpException -> {
                    var err: ErrorRespon
                    throwable.response()?.errorBody()?.string().toString().let {
                        err = Gson().fromJson(it, ErrorRespon::class.java)

                    }
                    ApiResponse.error(err.statusMessage ?: "error", null)
                }
                else -> ApiResponse.error(throwable.message ?: "error", null)
            }
        }
    }

    suspend fun getListTv(page: Int): ApiResponse<List<TvShow>?> {

        try {
            val listTv = apiService.getListTv(ApiConfig.LANGUAGE, page)
            return if (listTv.results != null)
                ApiResponse.success(listTv.results) else
                ApiResponse.empty("tidak ada data", null)

        } catch (throwable: Throwable) {
            return when (throwable) {
                is HttpException -> {
                    var err: ErrorRespon
                    throwable.response()?.errorBody()?.string().toString().let {
                        err = Gson().fromJson(it, ErrorRespon::class.java)
                    }
                    ApiResponse.error(err.statusMessage ?: "error", null)
                }
                else -> {
                    ApiResponse.error(throwable.message ?: "error", null)
                }

            }
        }
    }

    suspend fun getTv(id: Int): ApiResponse<TvDetail?> {
        try {
            return ApiResponse.success(apiService.getTvShow(id, ApiConfig.LANGUAGE))
        } catch (throwable: Throwable) {
            return when (throwable) {
                is HttpException -> {
                    var err: ErrorRespon
                    throwable.response()?.errorBody()?.string().toString().let {
                        err = Gson().fromJson(it, ErrorRespon::class.java)

                    }
                    ApiResponse.error(err.statusMessage ?: "error", null)
                }
                else -> ApiResponse.error(throwable.message ?: "error", null)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService).apply {
                    instance = this
                }
            }
    }
}