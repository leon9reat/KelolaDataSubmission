package com.medialink.keloladatasubmission.ui.fragment

import androidx.lifecycle.ViewModel
import com.medialink.keloladatasubmission.data.source.TheMovieRepository

abstract class BaseViewModel(private val theMovieRepo: TheMovieRepository) : ViewModel() {


}