package com.konkuk.nongboohae.remote.request

import com.google.gson.annotations.SerializedName
import com.konkuk.nongboohae.domain.Farm

data class JoinRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("profile_img_url")
    val profileImg: String?,
    @SerializedName("farm")
    val farm: Farm?
)
