package com.medialink.keloladatasubmission.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.di.Injection
import com.medialink.keloladatasubmission.ui.detail.movie.MovieDetViewModel
import com.medialink.keloladatasubmission.ui.detail.tv.TvDetViewModel
import com.medialink.keloladatasubmission.ui.favorite.movie.MovieFavViewModel
import com.medialink.keloladatasubmission.ui.favorite.tv.TvFavViewModel
import com.medialink.keloladatasubmission.ui.fragment.movie.MovieViewModel
import com.medialink.keloladatasubmission.ui.fragment.tv.TvViewModel

class ViewModelFactory
private constructor(private val mRepository: TheMovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                return TvViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavViewModel::class.java) -> {
                return MovieFavViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetViewModel::class.java) -> {
                return MovieDetViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvDetViewModel::class.java) -> {
                return TvDetViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvFavViewModel::class.java) -> {
                return TvFavViewModel(mRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

        companion object {
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
        }


}