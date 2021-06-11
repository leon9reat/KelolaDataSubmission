package com.medialink.keloladatasubmission.data.source.remote.repository

import com.medialink.keloladatasubmission.data.source.remote.response.tv.ListTv
import com.medialink.keloladatasubmission.data.source.remote.response.tv.TvDetail
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiService

open class TvRepository(private val apiService: ApiService): ITvRepository {
    override suspend fun getListTv(language: String, page: Int): ListTv {
        return apiService.getListTv(language, page)
    }

    override suspend fun getTvShow(id: Int, language: String): TvDetail {
        return apiService.getTvShow(id, language)
    }
}