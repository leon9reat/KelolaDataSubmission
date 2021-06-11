package com.medialink.keloladatasubmission.data.source.remote.repository

import com.google.gson.Gson
import com.medialink.keloladatasubmission.data.source.remote.response.error.ErrorRespon
import com.medialink.keloladatasubmission.data.source.remote.retrofit.*
import com.medialink.keloladatasubmission.utils.FakeWeb
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MovieRepositoryTest {

    private var mockWebServer = MockWebServer()
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/")) // note the URL is different from production one
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testResponApiOk() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(FakeWeb.MOVIE_RESPON)
            )

            val response = apiService.getListMovies(ApiConfig.LANGUAGE, 1)

            Assert.assertEquals(1, response.page)
            Assert.assertEquals(10000, response.totalResults)
            Assert.assertEquals(500, response.totalPages)
            Assert.assertEquals(460465, response.results?.first()?.id)
        }
    }

    @Test
    fun testResponApiError404() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(FakeWeb.ERROR404)
            )

            var err : ErrorRespon? = null

            try {
                apiService.getListMovies(ApiConfig.LANGUAGE, 1)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        err = ErrorRespon(
                            throwable.message,
                            false,
                            400
                        )
                    }
                    is HttpException -> {
                        throwable.response()?.errorBody()?.string().toString().let {
                            err = Gson().fromJson(it, ErrorRespon::class.java)
                        }
                    }
                    else -> {
                        err = ErrorRespon(
                            throwable.message,
                            false,
                            400
                        )
                    }
                }

            }

            Assert.assertEquals(34, err?.statusCode)
            Assert.assertEquals("The resource you requested could not be found.", err?.statusMessage)
        }
    }

    @Test
    fun testResponApiError401() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                    .setBody(FakeWeb.ERROR401)
            )

            var err : ErrorRespon? = null

            try {
                apiService.getListMovies(ApiConfig.LANGUAGE, 1)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        err = ErrorRespon(
                            throwable.message,
                            false,
                            400
                        )
                    }
                    is HttpException -> {
                        throwable.response()?.errorBody()?.string().toString().let {
                            err = Gson().fromJson(it, ErrorRespon::class.java)
                        }
                    }
                    else -> {
                        err = ErrorRespon(
                            throwable.message,
                            false,
                            400
                        )
                    }
                }

            }

            Assert.assertEquals(7, err?.statusCode)
            Assert.assertEquals("Invalid API key: You must be granted a valid key.", err?.statusMessage)
        }
    }

    @Test
    fun testGetMovieFromNetwork() {
        val myApiService = RetrofitClient.getApiService()
        val myRepo = MovieRepository(myApiService)
        runBlocking {
            val x = myRepo.getListMovie(ApiConfig.LANGUAGE, 1)

            // test list movie
            Assert.assertNotNull(x)
            Assert.assertEquals(20, x.results?.size)
            val id: Int = x.results?.get(0)?.id ?: 0

            // test detail movie
            val detail = myRepo.getMovie(id, ApiConfig.LANGUAGE)
            Assert.assertNotNull(detail)
            Assert.assertEquals(id, detail.id)
        }
    }
}