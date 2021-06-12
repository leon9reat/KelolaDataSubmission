package com.medialink.keloladatasubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.local.room.MovieDao

class LocalDataSource private constructor(
    private val mTheMovieDao: MovieDao
) {

    /* movie */
    fun getListMovie(): DataSource.Factory<Int, MovieDetailEntity> =
        mTheMovieDao.getList()

    fun getMovieCount(): Int = mTheMovieDao.getMovieCount()

    fun getMovie(id: Int): LiveData<MovieDetailEntity> =
        mTheMovieDao.getById(id)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieDetailEntity> =
        mTheMovieDao.getFavorite()

    fun insertMovie(listMovie: List<MovieDetailEntity>) {
        mTheMovieDao.insertAll(listMovie)
    }

    fun setFavoriteMovie(movie: MovieDetailEntity, state: Boolean) {
        movie.isFavorite = state
        mTheMovieDao.update(movie)
    }

    fun updateMovie(movie: MovieDetailEntity) {
        mTheMovieDao.update(movie)
    }

    /* tv show */
    fun getListTv(): DataSource.Factory<Int, TvDetailEntity> =
        mTheMovieDao.getListTv()

    fun getTvCount(): Int = mTheMovieDao.getTvCount()

    fun getTv(id: Int): LiveData<TvDetailEntity> =
        mTheMovieDao.getByIdTv(id)

    fun getFavoriteTv(): DataSource.Factory<Int, TvDetailEntity> =
        mTheMovieDao.getFavoriteTv()

    fun insertTv(listTv: List<TvDetailEntity>) {
        mTheMovieDao.insertAllTv(listTv)
    }

    fun setFavoriteTv(tv: TvDetailEntity, state: Boolean) {
        tv.isFavorite = state
        mTheMovieDao.updateTv(tv)
    }

    fun updateTv(tv: TvDetailEntity) {
        mTheMovieDao.updateTv(tv)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao).apply {
                INSTANCE = this
            }

    }
}