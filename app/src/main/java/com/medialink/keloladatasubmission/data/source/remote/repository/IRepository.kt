package com.medialink.keloladatasubmission.data.source.remote.repository

import com.medialink.keloladatasubmission.data.source.remote.response.movie.ListMovie
import com.medialink.keloladatasubmission.data.source.remote.response.movie.MovieDetail
import com.medialink.keloladatasubmission.data.source.remote.response.tv.ListTv
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvDetail

interface IRepository {

}

interface IMovieRepository : IRepository {
    suspend fun getListMovie(language: String, page: Int): ListMovie
    suspend fun getMovie(id: Int, language: String): MovieDetail
}

interface ITvRepository : IRepository {
    suspend fun getListTv(language: String, page: Int): ListTv
    suspend fun getTvShow(id: Int, language: String): TvDetail
}