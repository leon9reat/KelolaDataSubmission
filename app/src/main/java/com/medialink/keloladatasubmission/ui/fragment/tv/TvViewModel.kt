package com.medialink.keloladatasubmission.ui.fragment.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.medialink.keloladatasubmission.data.source.TheMovieRepository
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.vo.Resource

class TvViewModel(private val theMovieRepo: TheMovieRepository): ViewModel() {

    fun getListTv(): LiveData<Resource<PagedList<TvDetailEntity>>> =
        theMovieRepo.getListTv()

    fun setFavoriteTv(tv: TvDetailEntity) {
        val newState = !tv.isFavorite
        theMovieRepo.setFavoriteTv(tv, newState)
    }

}