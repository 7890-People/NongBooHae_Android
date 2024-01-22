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

    fun formattingString(s: String): String {
        // <br/> 기준으로 나누기 => 문장 앞에 - 삽입, 뒤에 \n 삽입
        val sentences = s.split("<br/>")
        val formattedText = StringBuilder()
        for (sentence in sentences) {
            formattedText.append("- ").append(sentence.trim()).append("\n")
        }
        return formattedText.toString()
    }

    fun requestDiagnosisResult(filePath: String?, plantName: String) = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            Log.d("retrofit", filePath.toString())
            val file = File(filePath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val plantImg = MultipartBody.Part.createFormData("img", file.name, requestFile) //폼데이터
            val plantNameRequest: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), plantName)
            repository.postDiagnosis(plantImg, plantNameRequest)
        }
        response.byState(
            onSuccess = {
                Log.d("retrofit", "통신 성공")
                val formattedResponse  =  DiagnosisResultResponse(
                        it.diseaseName,
                        formattingString(it.condition),
                        formattingString(it.symptoms),
                        formattingString(it.preventionMethod),
                        it.diseaseImg)
                _diagnosisResult.value = formattedResponse
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