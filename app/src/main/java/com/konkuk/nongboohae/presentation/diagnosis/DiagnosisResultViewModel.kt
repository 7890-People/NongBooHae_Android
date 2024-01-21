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
import java.io.File

class DiagnosisResultViewModel(private val repository: DiagnosisResultRepository) : ViewModel() {

    private val _diagnosisResult = MutableLiveData<DiagnosisResultResponse>()
    val diagnosisResultResponse : LiveData<DiagnosisResultResponse> = _diagnosisResult


    fun requestDiagnosisResult(filePath: String?, plantName: String) = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            Log.d("retrofit", filePath.toString())
            val file = File(filePath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val plantImg = MultipartBody.Part.createFormData("plantImg", file.name, requestFile) //폼데이터
            val plantNameRequest: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), plantName)
            repository.postDiagnosis(plantImg, plantNameRequest)
            //repository.postDiagnosis(DiagnosisResultRequest("포도","img_url"))
        }
        response.byState(
            onSuccess = {
                Log.d("retrofit", "통신 성공")
                _diagnosisResult.value = it
            },
            onFailure = {
                Log.d("retrofit", it.toString())
            },
            onLoading = {

            }
        )
    }
}