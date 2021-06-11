package com.medialink.keloladatasubmission.di

import android.content.Context
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.data.source.local.LocalDataSource
import com.medialink.keloladatasubmission.data.source.local.room.TheMovieDatabase
import com.medialink.keloladatasubmission.data.source.remote.RemoteDataSource
import com.medialink.keloladatasubmission.data.source.remote.retrofit.RetrofitClient
import com.medialink.keloladatasubmission.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): TheMovieRepository {
        val mDatabase = TheMovieDatabase.getInstance(context)
        val mRemote = RemoteDataSource.getInstance(RetrofitClient.getApiService())
        val mLocal = LocalDataSource.getInstance(mDatabase.movieDao())
        val appExecutors = AppExecutors()

        return TheMovieRepository.getInstance(mRemote, mLocal, appExecutors)
    }
}