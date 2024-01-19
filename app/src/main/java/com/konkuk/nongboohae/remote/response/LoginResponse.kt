package com.konkuk.nongboohae.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val token: String?
)
