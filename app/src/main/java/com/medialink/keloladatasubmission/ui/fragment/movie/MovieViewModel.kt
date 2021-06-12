package com.medialink.keloladatasubmission.ui.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.vo.Resource

class MovieViewModel(private val theMovieRepo: TheMovieRepository) : ViewModel() {

    fun getListMovie(): LiveData<Resource<PagedList<MovieDetailEntity>>> =
        theMovieRepo.getListMovie()

    fun setFavoriteMovie(movie: MovieDetailEntity) {
            val newState = !movie.isFavorite
            theMovieRepo.setFavoriteMovie(movie, newState)
    }

}