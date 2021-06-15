package com.medialink.keloladatasubmission.ui.favorite.tv

import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

interface ITvFavFragment {

    fun tvDeleteClick(tv: TvDetailEntity)

    fun tvDetail(id: Int)
}