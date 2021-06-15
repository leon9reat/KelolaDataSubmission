package com.medialink.keloladatasubmission.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.medialink.keloladatasubmission.data.source.local.LocalDataSource
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.MovieUpdate
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvUpdate
import com.medialink.keloladatasubmission.data.source.remote.RemoteDataSource
import com.medialink.keloladatasubmission.data.source.remote.response.ApiResponse
import com.medialink.keloladatasubmission.data.source.remote.response.movie.Movie
import com.medialink.keloladatasubmission.data.source.remote.response.movie.MovieDetail
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvDetail
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvShow
import com.medialink.keloladatasubmission.utils.AppExecutors
import com.medialink.keloladatasubmission.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("DEPRECATION")
class TheMovieRepository
private constructor(
    private val mRemote: RemoteDataSource,
    private val mLocal: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    TheMovieDataSource {
    override fun getListMovie(): LiveData<Resource<PagedList<MovieDetailEntity>>> {

        return object :
            NetworkBoundResource<PagedList<MovieDetailEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieDetailEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                val listData = LivePagedListBuilder(mLocal.getListMovie(), config).build()
                Log.d("TAG", "loadFromDB: ${listData.value}")
                return listData
            }

            override fun shouldFetch(data: PagedList<MovieDetailEntity>?): Boolean {
                var i = 0
                runBlocking {
                    launch(Dispatchers.IO) {
                        i = mLocal.getMovieCount()
                    }
                }
                Log.d("TAG", "getMovieCount: $i")
                return i <= 0


                //Log.d("TAG", "shouldFetch: ${data?.size}")
                //return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                val listMovie = MutableLiveData<ApiResponse<List<Movie>>>()
                runBlocking {
                    listMovie.postValue(mRemote.getListMovies(1) as ApiResponse<List<Movie>>?)
                }
                return listMovie
            }

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieDetailEntity>()
                for (response in data) {
                    val movie = MovieDetailEntity(
                        id = response.id ?: 0,
                        adult = response.adult,
                        backdropPath = response.backdropPath,
                        originalLanguage = response.originalLanguage,
                        originalTitle = response.originalTitle,
                        overview = response.overview,
                        popularity = response.popularity,
                        posterPath = response.posterPath,
                        releaseDate = response.releaseDate,
                        title = response.title,
                        voteAverage = response.voteAverage,
                        voteCount = response.voteCount
                    )

                    movieList.add(movie)
                }
                Log.d("TAG", "saveCallResult: ${movieList.size}")
                mLocal.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getMovie(id: Int): LiveData<Resource<MovieDetailEntity>> {
        return object : NetworkBoundResource<MovieDetailEntity, MovieDetail>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieDetailEntity> {
                return mLocal.getMovie(id)
            }

            override fun shouldFetch(data: MovieDetailEntity?): Boolean {
                return data == null || !data.isDetail
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetail>> {
                val movie = MutableLiveData<ApiResponse<MovieDetail>>()
                runBlocking {
                    movie.value = (mRemote.getMovie(id) as ApiResponse<MovieDetail>?)
                }
                return movie
            }

            override fun saveCallResult(data: MovieDetail) {
                val movie = MovieUpdate(
                    id = data.id ?: 0,
                    adult = data.adult,
                    backdropPath = data.backdropPath,
                    budget = data.budget,
                    homepage = data.homepage,
                    imdbId = data.imdbId,
                    originalLanguage = data.originalLanguage,
                    originalTitle = data.originalTitle,
                    overview = data.overview,
                    popularity = data.popularity,
                    posterPath = data.posterPath,
                    releaseDate = data.releaseDate,
                    revenue = data.revenue,
                    runtime = data.runtime,
                    status = data.status,
                    tagline = data.tagline,
                    title = data.title,
                    video = data.video,
                    voteAverage = data.voteAverage,
                    voteCount = data.voteCount,
                    isDetail = true
                )

                //mLocal.updateMovie(movie)
                mLocal.updateMoviePartial(movie)
            }

        }.asLiveData()
    }

    override fun getListTv(): LiveData<Resource<PagedList<TvDetailEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvDetailEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvDetailEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(mLocal.getListTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvDetailEntity>?): Boolean {
                var i = 0
                runBlocking {
                    launch(Dispatchers.IO) {
                        i = mLocal.getTvCount()
                    }
                }
                Log.d("TAG", "getTvCount: $i")
                return i <= 0
                //return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> {
                val listTv = MutableLiveData<ApiResponse<List<TvShow>>>()
                runBlocking {
                    listTv.value = (mRemote.getListTv(1) as ApiResponse<List<TvShow>>?)
                }
                return listTv
            }

            override fun saveCallResult(data: List<TvShow>) {
                val tvList = ArrayList<TvDetailEntity>()
                for (response in data) {
                    val tv = TvDetailEntity(
                        posterPath = response.posterPath,
                        popularity = response.popularity,
                        id = response.id ?: 0,
                        backdropPath = response.backdropPath,
                        voteAverage = response.voteAverage,
                        overview = response.overview,
                        firstAirDate = response.firstAirDate,
                        originalLanguage = response.originalLanguage,
                        voteCount = response.voteCount,
                        name = response.name,
                        originalName = response.originalName
                    )
                    tvList.add(tv)
                }

                mLocal.insertTv(tvList)
            }

        }.asLiveData()
    }

    override fun getTv(id: Int): LiveData<Resource<TvDetailEntity>> {
        return object : NetworkBoundResource<TvDetailEntity, TvDetail>(appExecutors) {
            override fun loadFromDB(): LiveData<TvDetailEntity> {
                return mLocal.getTv(id)
            }

            override fun shouldFetch(data: TvDetailEntity?): Boolean {
                return data == null || !data.isDetail
            }

            override fun createCall(): LiveData<ApiResponse<TvDetail>> {
                val tv = MutableLiveData<ApiResponse<TvDetail>>()
                runBlocking {
                    tv.value = (mRemote.getTv(id) as ApiResponse<TvDetail>?)
                }
                return tv
            }

            override fun saveCallResult(data: TvDetail) {
                val tv = TvUpdate(
                    backdropPath = data.backdropPath,
                    firstAirDate = data.firstAirDate,
                    homepage = data.homepage,
                    id = data.id ?: 0,
                    inProduction = data.inProduction,
                    lastAirDate = data.lastAirDate,
                    name = data.name,
                    numberOfEpisodes = data.numberOfEpisodes,
                    numberOfSeasons = data.numberOfSeasons,
                    originalLanguage = data.originalLanguage,
                    originalName = data.originalName,
                    overview = data.overview,
                    popularity = data.popularity,
                    posterPath = data.posterPath,
                    status = data.status,
                    tagline = data.tagline,
                    type = data.type,
                    voteAverage = data.voteAverage,
                    voteCount = data.voteCount,
                    isDetail = true
                )

                mLocal.updateTvPartial(tv)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(mLocal.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(mLocal.getFavoriteTv(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieDetailEntity, state: Boolean) {
        appExecutors.diskIO().execute { mLocal.setFavoriteMovie(movie, state) }
    }

    override fun setFavoriteTv(tv: TvDetailEntity, state: Boolean) {
        appExecutors.diskIO().execute { mLocal.setFavoriteTv(tv, state) }
    }

    companion object {
        @Volatile
        private var instance: TheMovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TheMovieRepository =
            instance ?: synchronized(this) {
                TheMovieRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}