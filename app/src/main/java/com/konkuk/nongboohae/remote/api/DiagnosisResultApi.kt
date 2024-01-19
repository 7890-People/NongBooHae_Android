package com.konkuk.nongboohae.remote.api

import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
import com.konkuk.nongboohae.remote.response.DiseaseListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface DiagnosisResultApi {
    @Multipart
    @POST("/diseases")
    suspend fun postDiagnosis(
        @Header("Authorization") token: String? = "Bearer [tokenString]}",
        @Part plantImg: MultipartBody.Part?,
        @Part("plantName") plantName: RequestBody
    ): Response<DiagnosisResultResponse>
}