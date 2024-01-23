package com.konkuk.mocacong.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.nongboohae.presentation.main.MainPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MainViewModel() : ViewModel() {

    private val _pageFlow = MutableStateFlow(MainPage.HISTORY)
    val pageFlow = _pageFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainPage.HISTORY)

    fun gotoPage(page: MainPage) {
        viewModelScope.launch {
            _pageFlow.emit(page)
        }
    }

    private val _btnvFlow = MutableStateFlow(true)
    val btnvFlow = _btnvFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    fun setBtnvVisibility(visibility: Boolean) {
        viewModelScope.launch {
            _btnvFlow.emit(visibility)
        }
    }

}