package com.medialink.keloladatasubmission.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.di.Injection
import com.medialink.keloladatasubmission.ui.fragment.MovieViewModel
import com.medialink.keloladatasubmission.ui.fragment.TvViewModel

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
            /*modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                return CourseReaderViewModel(mAcademyRepository) as T
            }*/
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