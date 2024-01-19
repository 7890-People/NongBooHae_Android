package com.konkuk.nongboohae.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("user_id")
    val id: String
)
