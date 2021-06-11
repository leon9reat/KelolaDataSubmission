package com.medialink.keloladatasubmission.data.source.remote.repository

import com.medialink.keloladatasubmission.data.source.remote.response.movie.ListMovie
import com.medialink.keloladatasubmission.data.source.remote.response.movie.MovieDetail
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiService

open class MovieRepository(private val apiService: ApiService): IMovieRepository {
    override suspend fun getListMovie(language: String, page: Int): ListMovie {
        return apiService.getListMovies(language, page)
    }

    override suspend fun getMovie(id: Int, language: String): MovieDetail {
        return apiService.getMovie(id, language)
    }
}