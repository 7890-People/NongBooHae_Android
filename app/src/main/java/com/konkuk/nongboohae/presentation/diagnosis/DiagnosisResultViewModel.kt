package com.konkuk.nongboohae.presentation.diagnosis

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
import com.konkuk.nongboohae.util.SingleLiveData
import com.konkuk.nongboohae.util.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.MultipartBody
import okhttp3.RequestBody

class DiagnosisResultViewModel(private val repository: DiagnosisResultRepository) : ViewModel() {
    private val _diagnosisResultResponse = SingleLiveData<ApiState<DiagnosisResultResponse>>()
    lateinit var diagnosisResultResponse : DiagnosisResultResponse


    fun requestDiagnosisResultAsync(plantImg: MultipartBody.Part, plantName: RequestBody) =
        viewModelScope.async(Dispatchers.IO){
            val value = repository.postDiagnosis(plantImg, plantName)
            _diagnosisResultResponse.postValue(value)
            return@async value
        }
}