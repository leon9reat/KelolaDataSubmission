package com.medialink.keloladatasubmission.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.MovieUpdate
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvUpdate

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_detail")
    fun getList(): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT COUNT(id) FROM movie_detail")
    fun getMovieCount(): Int

    @Query("SELECT * FROM movie_detail WHERE is_favorite = 1")
    fun getFavorite(): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getById(id: Int): LiveData<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movie: List<MovieDetailEntity>)

    @Update
    fun update(movie: MovieDetailEntity)

    @Update(entity = MovieDetailEntity::class)
    fun updatePartial(movie: MovieUpdate)


    @Query("SELECT * FROM tv_detail")
    fun getListTv(): DataSource.Factory<Int, TvDetailEntity>

    @Query("SELECT COUNT(id) FROM tv_detail")
    fun getTvCount(): Int

    @Query("SELECT * FROM tv_detail WHERE is_favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvDetailEntity>

    @Query("SELECT * FROM tv_detail WHERE id = :id")
    fun getByIdTv(id: Int): LiveData<TvDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTv(tv: List<TvDetailEntity>)

    @Update
    fun updateTv(tv: TvDetailEntity)

    @Update(entity = TvDetailEntity::class)
    fun updateTvPartial(tv: TvUpdate)

    @RawQuery(observedEntities = [MovieDetailEntity::class])
    fun getRawMovieCount(query: SupportSQLiteQuery): Int

}