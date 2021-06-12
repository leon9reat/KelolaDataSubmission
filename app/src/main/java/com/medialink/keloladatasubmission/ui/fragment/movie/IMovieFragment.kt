package com.medialink.keloladatasubmission.ui.fragment.movie

import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity

interface IMovieFragment {

    fun movieFavoriteClick(movie: MovieDetailEntity)

}