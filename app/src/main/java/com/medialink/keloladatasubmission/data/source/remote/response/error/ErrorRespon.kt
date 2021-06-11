package com.medialink.keloladatasubmission.data.source.remote.response.error


import com.google.gson.annotations.SerializedName

data class ErrorRespon(
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("status_code")
    val statusCode: Int?
)