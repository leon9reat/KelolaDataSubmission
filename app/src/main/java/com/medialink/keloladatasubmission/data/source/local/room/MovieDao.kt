package com.medialink.keloladatasubmission.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_detail")
    fun getList(): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT * FROM movie_detail WHERE is_favorite = 1")
    fun getFavorite(): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getById(id: Int): LiveData<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie: List<MovieDetailEntity>)

    @Update
    fun update(movie: MovieDetailEntity)



    @Query("SELECT * FROM tv_detail")
    fun getListTv(): DataSource.Factory<Int, TvDetailEntity>

    @Query("SELECT * FROM tv_detail WHERE is_favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvDetailEntity>

    @Query("SELECT * FROM tv_detail WHERE id = :id")
    fun getByIdTv(id: Int): LiveData<TvDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTv(tv: List<TvDetailEntity>)

    @Update
    fun updateTv(tv: TvDetailEntity)

}