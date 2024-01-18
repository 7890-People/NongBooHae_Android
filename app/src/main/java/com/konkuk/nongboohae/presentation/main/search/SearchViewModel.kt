package com.konkuk.nongboohae.presentation.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    lateinit var entireList: List<DiseaseListPresentModel>

    private val _diseaseList = MutableLiveData<List<DiseaseListPresentModel>>()
    val diseaseList: LiveData<List<DiseaseListPresentModel>> = _diseaseList

    fun requestDiseaseList(category: String) = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            repository.getDiseases(category)
        }
        response.byState(
            onSuccess = {
                _diseaseList.value = it.diseases
                if(category=="all") entireList = it.diseases
            },
            onFailure = {

            },
            onLoading = {

            }
        )

    }

}