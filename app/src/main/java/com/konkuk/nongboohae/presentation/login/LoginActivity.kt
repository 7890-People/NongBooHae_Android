package com.konkuk.nongboohae.presentation.login

import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityLoginBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val TAG: String = "LoginActivity"
    override val layoutRes: Int = R.layout.activity_login
    lateinit var viewModel: LoginViewModel

    override fun initViewModel() {
        viewModel = createViewModel(MemberRepository())
    }

    override fun afterViewCreated() {

    }


}