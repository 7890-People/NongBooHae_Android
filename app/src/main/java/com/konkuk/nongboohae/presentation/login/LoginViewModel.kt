package com.konkuk.nongboohae.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(val memberRepository: MemberRepository) : ViewModel() {
    fun requestLogin(id: String, email: String) {

    }

    private val _pageFlow = MutableStateFlow(LoginPage.LOGIN)
    val pageFlow = _pageFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, LoginPage.LOGIN)

    fun goto(page: LoginPage) {
        viewModelScope.launch {
            _pageFlow.emit(page)
        }
    }

}
