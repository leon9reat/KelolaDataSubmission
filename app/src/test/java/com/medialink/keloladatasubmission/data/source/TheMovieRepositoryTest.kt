package com.medialink.keloladatasubmission.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.medialink.keloladatasubmission.data.source.local.LocalDataSource
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.remote.RemoteDataSource
import com.medialink.keloladatasubmission.utils.AppExecutors
import com.medialink.keloladatasubmission.utils.DataDummy
import com.medialink.keloladatasubmission.utils.LiveDataTestUtil
import com.medialink.keloladatasubmission.utils.PagedListUtil
import com.medialink.keloladatasubmission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TheMovieRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)

    private val local = Mockito.mock(LocalDataSource::class.java)

    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val repository = FakeMovieRepository(remote, local, appExecutors)

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getListMovie() {
        val dsFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDetailEntity>
        Mockito.`when`(local.getListMovie()).thenReturn(dsFactory)
        val x = local.getListMovie()
        Assert.assertNotNull(x)

//        val config = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(20)
//            .setPageSize(20)
//            .build()
//        LivePagedListBuilder(x, config).build()

        repository.getListMovie()

        //val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyCourses()))
    }

    @Test
    fun getListFavMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDetailEntity>
        Mockito.`when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovie()

        val dummyMovieList = DataDummy.genMovieList()
        val listFavorite = Resource.success(PagedListUtil.mockPagedList(DataDummy.genMovieList()))
        verify(local).getFavoriteMovie()
        Assert.assertNotNull(listFavorite)
        Assert.assertEquals(dummyMovieList.size.toLong(), listFavorite.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<MovieDetailEntity>()
        dummyMovie.value = DataDummy.genMovieList()[0]

        Mockito.`when`(local.getMovie(1)).thenReturn(dummyMovie)
        val liveMovie = LiveDataTestUtil.getValue(repository.getMovie(1))
        verify(local).getMovie(1)

        Assert.assertNotNull(liveMovie)
        Assert.assertEquals(dummyMovie.value, liveMovie.data)

    }

    @Test
    fun getTv() {
        val dummyTv = MutableLiveData<TvDetailEntity>()
        dummyTv.value = DataDummy.genTvList()[0]

        Mockito.`when`(local.getTv(1)).thenReturn(dummyTv)
        val liveTv = LiveDataTestUtil.getValue(repository.getTv(1))
        verify(local).getTv(1)

        Assert.assertNotNull(liveTv)
        Assert.assertEquals(dummyTv.value, liveTv.data)
    }
}