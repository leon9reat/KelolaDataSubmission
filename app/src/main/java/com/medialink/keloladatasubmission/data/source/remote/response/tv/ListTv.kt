package com.medialink.keloladatasubmission.data.source.remote.response.tv


import com.google.gson.annotations.SerializedName

data class ListTv(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<TvShow>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)