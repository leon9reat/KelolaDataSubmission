package com.medialink.keloladatasubmission.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.vo.Resource

class TvDetViewModel(private val mRepository: TheMovieRepository) : ViewModel() {

    fun getTv(id: Int): LiveData<Resource<TvDetailEntity>> =
        mRepository.getTv(id)

    fun setFavoriteTv(tv: TvDetailEntity) {
        val newState = !tv.isFavorite
        mRepository.setFavoriteTv(tv, newState)
    }

}