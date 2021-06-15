package com.medialink.keloladatasubmission.ui.favorite.movie

import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity

interface IMovieFavFargment {

    fun movieDeleteClick(movie: MovieDetailEntity)

    fun movieDetail(id: Int)

}