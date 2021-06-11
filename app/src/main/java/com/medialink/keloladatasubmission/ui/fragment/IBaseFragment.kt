package com.medialink.keloladatasubmission.ui.fragment

import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

interface IBaseFragment {

    fun onItemClick(id: Int, idType: Int)

    fun movieFavoriteClick(movie: MovieDetailEntity)

    fun tvFavoriteClick(tv: TvDetailEntity)
}