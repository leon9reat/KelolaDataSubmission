package com.medialink.keloladatasubmission.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.vo.Resource

interface TheMovieDataSource {

    fun getListMovie(): LiveData<Resource<PagedList<MovieDetailEntity>>>

    fun getMovie(id: Int):  LiveData<Resource<MovieDetailEntity>>

    fun getListTv(): LiveData<Resource<PagedList<TvDetailEntity>>>

    fun getTv(id: Int): LiveData<Resource<TvDetailEntity>>

    fun getFavoriteMovie(): LiveData<Resource<PagedList<MovieDetailEntity>>>

    fun getFavoriteTv(): LiveData<Resource<PagedList<TvDetailEntity>>>

    fun setFavoriteMovie(movie: MovieDetailEntity, state: Boolean)

    fun setFavoriteTv(tv: TvDetailEntity, state: Boolean)

}