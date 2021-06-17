package com.medialink.keloladatasubmission.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.medialink.keloladatasubmission.data.source.local.LocalDataSource
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.remote.RemoteDataSource
import com.medialink.keloladatasubmission.utils.AppExecutors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TheMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    @Mock
    private val repository = FakeMovieRepository(remote, local, appExecutors)

    @Test
    fun getListMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDetailEntity>
        Mockito.`when`(local.getListMovie()).thenReturn(dataSourceFactory)
        repository.getListMovie()

        //val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyCourses()))
    }
}