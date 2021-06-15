package com.medialink.keloladatasubmission.ui.fragment.tv

import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

interface ITvFragment {

    fun tvFavoriteClick(tv: TvDetailEntity)

    fun tvDetail(id: Int)
}