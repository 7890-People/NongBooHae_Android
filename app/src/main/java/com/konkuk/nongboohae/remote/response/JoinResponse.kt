package com.konkuk.nongboohae.remote.response

import com.google.gson.annotations.SerializedName

data class JoinResponse(
    @SerializedName("access_token")
    val token: String
)
