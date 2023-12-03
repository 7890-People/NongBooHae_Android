package com.konkuk.nongboohae.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityLoginBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.util.factory.ViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val TAG: String = "LoginActivity"
    override val layoutRes: Int = R.layout.activity_login
    lateinit var viewModel: LoginViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(MemberRepository()))[LoginViewModel::class.java]
    }
    override fun afterViewCreated() {

    }


}