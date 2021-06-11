package com.medialink.keloladatasubmission.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.local.room.MovieDao

class LocalDataSource private constructor(
    private val mMovieDao: MovieDao
) {

    /* movie */
    fun getListMovie(): DataSource.Factory<Int, MovieDetailEntity> =
        mMovieDao.getList()

    fun getMovie(id: Int): LiveData<MovieDetailEntity> =
        mMovieDao.getById(id)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieDetailEntity> =
        mMovieDao.getFavorite()

    fun insertMovie(listMovie: List<MovieDetailEntity>) {
        mMovieDao.insertAll(listMovie)
    }

    fun setFavoriteMovie(movie: MovieDetailEntity, state: Boolean) {
        Log.d("TAG", "setFavoriteMovie: ${state}")
        movie.isFavorite = state
        mMovieDao.update(movie)
    }

    fun updateMovie(movie: MovieDetailEntity) {
        mMovieDao.update(movie)
    }

    /* tv show */
    fun getListTv(): DataSource.Factory<Int, TvDetailEntity> =
        mMovieDao.getListTv()

    fun getTv(id: Int): LiveData<TvDetailEntity> =
        mMovieDao.getByIdTv(id)

    fun getFavoriteTv(): DataSource.Factory<Int, TvDetailEntity> =
        mMovieDao.getFavoriteTv()

    fun insertTv(listTv: List<TvDetailEntity>) {
        mMovieDao.insertAllTv(listTv)
    }

    fun setFavoriteTv(tv: TvDetailEntity, state: Boolean) {
        tv.isFavorite = state
        mMovieDao.updateTv(tv)
    }

    fun updateTv(tv: TvDetailEntity) {
        mMovieDao.updateTv(tv)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao).apply {
                INSTANCE = this
            }

    }
}