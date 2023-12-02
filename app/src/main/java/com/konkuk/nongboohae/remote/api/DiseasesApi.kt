package com.konkuk.nongboohae.remote.api

import com.konkuk.nongboohae.remote.response.DiseaseListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DiseasesApi {

    @GET("diseases/{category}")
    suspend fun getDiseases(
        @Header("Authorization") token: String? = "Bearer [tokenString]}",
        @Path("category") category: String
    ): Response<DiseaseListResponse>
}