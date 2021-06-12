package com.medialink.keloladatasubmission.utils

import androidx.annotation.StringRes
import com.medialink.keloladatasubmission.R

object AppConfig {
    const val TYPE_MOVIE = 1

    const val TYPE_TV_SHOW = 2

    @StringRes
    val TAB_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
}