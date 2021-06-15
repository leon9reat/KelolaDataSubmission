package com.medialink.keloladatasubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.MovieUpdate
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvUpdate
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

    fun updateMoviePartial(movie: MovieUpdate) {
        mTheMovieDao.updatePartial(movie)
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

    fun updateTvPartial(tv: TvUpdate) {
        mTheMovieDao.updateTvPartial(tv)
    }

    fun getRawMovieCount(isFavorite: Boolean): Int {
        var query: SimpleSQLiteQuery
        if (isFavorite) {
            query = SimpleSQLiteQuery("SELECT COUNT(id) FROM movie_detail WHERE is_favorite = 1")
        } else {
            query = SimpleSQLiteQuery("SELECT COUNT(id) FROM movie_detail")
        }
        return mTheMovieDao.getRawMovieCount(query)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao).apply {
                INSTANCE = this
            }

    }
}