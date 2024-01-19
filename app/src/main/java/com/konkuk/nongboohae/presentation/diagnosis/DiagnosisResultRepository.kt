package com.konkuk.nongboohae.presentation.diagnosis

import com.konkuk.nongboohae.presentation.base.BaseRepository
import com.konkuk.nongboohae.remote.api.DiagnosisResultApi
import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
import com.konkuk.nongboohae.util.network.ApiState
import com.konkuk.nongboohae.util.network.RetrofitClient
import okhttp3.MultipartBody
import okhttp3.RequestBody

class DiagnosisResultRepository :BaseRepository() {
    private val api = RetrofitClient.create(DiagnosisResultApi::class.java)

    suspend fun postDiagnosis(plantImg: MultipartBody.Part, plantName: RequestBody): ApiState<DiagnosisResultResponse> =
        makeRequest { api.postDiagnosis(plantImg = plantImg, plantName = plantName) }
}