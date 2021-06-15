package com.medialink.keloladatasubmission.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.vo.Resource

class MovieDetViewModel(private val mRepository: TheMovieRepository) : ViewModel() {

    fun getMovie(id: Int): LiveData<Resource<MovieDetailEntity>> =
        mRepository.getMovie(id)

    fun setFavoriteMovie(movie: MovieDetailEntity) {
        val newState = !movie.isFavorite
        mRepository.setFavoriteMovie(movie, newState)
    }
}