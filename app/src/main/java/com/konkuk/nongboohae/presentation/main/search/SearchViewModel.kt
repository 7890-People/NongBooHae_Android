package com.konkuk.nongboohae.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.remote.response.DiseaseListResponse
import com.konkuk.nongboohae.util.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(val repository: SearchRepository) : ViewModel() {

    val mDiseaseListResponse = MutableLiveData<ApiState<DiseaseListResponse>>()
    val diseaseListResponse: LiveData<ApiState<DiseaseListResponse>> = mDiseaseListResponse

    fun requestDiseaseList(category: String) = viewModelScope.launch(Dispatchers.IO) {
        mDiseaseListResponse.postValue(repository.getDiseases(category))
    }

}