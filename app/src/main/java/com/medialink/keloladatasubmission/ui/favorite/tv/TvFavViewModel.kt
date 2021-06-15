package com.medialink.keloladatasubmission.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

class TvFavViewModel(private val mRepository: TheMovieRepository) : ViewModel() {

    fun getListTv(): LiveData<PagedList<TvDetailEntity>> =
        mRepository.getFavoriteTv()

    fun setFavoriteTv(tv: TvDetailEntity) {
        val newState = !tv.isFavorite
        mRepository.setFavoriteTv(tv, newState)
    }
}