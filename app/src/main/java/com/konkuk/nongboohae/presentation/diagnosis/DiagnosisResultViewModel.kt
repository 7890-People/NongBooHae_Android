package com.konkuk.nongboohae.presentation.diagnosis

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
import com.konkuk.nongboohae.util.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class DiagnosisResultViewModel(private val repository: DiagnosisResultRepository) : ViewModel() {

    private val _diagnosisResultResponse = MutableLiveData<ApiState<DiagnosisResultResponse>>()
    val diagnosisResultResponse: LiveData<ApiState<DiagnosisResultResponse>> = _diagnosisResultResponse

    var diagnosisResultTemp : DiagnosisResultResponse? = null

    fun formattingString(s: String): String {
        // <br/> 기준으로 나누기 => 문장 앞에 - 삽입, 뒤에 \n 삽입
        val sentences = s.split("<br/>")
        val formattedText = StringBuilder()
        for (sentence in sentences) {
            formattedText.append("- ").append(sentence.trim()).append("\n")
        }
        return formattedText.toString()
    }

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
        _diagnosisResultResponse.value = response
    }
}