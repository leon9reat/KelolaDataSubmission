package com.medialink.keloladatasubmission.data.source.remote.response.movie


import com.google.gson.annotations.SerializedName

data class ListMovie(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Movie>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)