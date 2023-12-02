package com.konkuk.nongboohae.presentation.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel
import com.konkuk.nongboohae.remote.response.DiseaseListResponse
import com.konkuk.nongboohae.util.SingleLiveData
import com.konkuk.nongboohae.util.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    lateinit var entireList: List<DiseaseListPresentModel>
    val diseaseListResponse = SingleLiveData<ApiState<DiseaseListResponse>>()

    fun requestDiseaseListAsync(category: String) = viewModelScope.async(Dispatchers.IO) {
        val value = repository.getDiseases(category)
        diseaseListResponse.postValue(value)
        return@async value
    }

}