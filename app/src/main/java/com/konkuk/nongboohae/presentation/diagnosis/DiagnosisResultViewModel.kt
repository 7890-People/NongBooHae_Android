package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class DiagnosisResultViewModel(private val repository: DiagnosisResultRepository) : ViewModel() {

    private val _diagnosisResult = MutableLiveData<DiagnosisResultResponse>()
    val diagnosisResultResponse : LiveData<DiagnosisResultResponse> = _diagnosisResult


    fun requestDiagnosisResult(filePath: String, plantName: String) = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            Log.d("retrofit", filePath)
            val file = File(filePath)
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val plantImg = MultipartBody.Part.createFormData("img", file.name, requestFile) //폼데이터
            val plantNameRequest: RequestBody =
                plantName.toRequestBody("text/plain".toMediaTypeOrNull())
            repository.postDiagnosis(plantImg, plantNameRequest)
        }
        response.byState(
            onSuccess = {
                Log.d("retrofit", "통신 성공")
                _diagnosisResult.value = it
                Log.d("retrofit", it.toString())
            },
            onFailure = {
                Log.d("retrofit", it.toString())
            },
            onLoading = {

            }
        )
    }
}