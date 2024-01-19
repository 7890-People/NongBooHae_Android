package com.konkuk.nongboohae.presentation.login

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityLoginBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.util.factory.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val TAG: String = "LoginActivity"
    override val layoutRes: Int = R.layout.activity_login
    lateinit var viewModel: LoginViewModel


    private val loginFragment by lazy {
        supportFragmentManager.findFragmentByTag(LoginFragment::class.java.name)
            ?: LoginFragment()
    }

    private val joinFragment1 by lazy {
        supportFragmentManager.findFragmentByTag(JoinFragment1::class.java.name)
            ?: JoinFragment1()
    }

    private val joinFragment2 by lazy {
        supportFragmentManager.findFragmentByTag(JoinFragment2::class.java.name)
            ?: JoinFragment2()
    }

    private val joinFragment3 by lazy {
        supportFragmentManager.findFragmentByTag(JoinFragment3::class.java.name)
            ?: JoinFragment3()
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MemberRepository())
        )[LoginViewModel::class.java]
    }

    override fun afterViewCreated() {
        collectPage()
        setBackBtn()
    }

    private fun getFragment(page: LoginPage): Fragment {
        return when (page) {
            LoginPage.LOGIN -> loginFragment
            LoginPage.JOIN1 -> joinFragment1
            LoginPage.JOIN2 -> joinFragment2
            LoginPage.JOIN3 -> joinFragment3
        }
    }

    private fun collectPage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                var prevPage = viewModel.pageFlow.value
                viewModel.pageFlow.collect { page ->
                    val preFragment = getFragment(prevPage)
                    val fragment = getFragment(page)
                    supportFragmentManager.commit {
                        if (preFragment != fragment) hide(preFragment)
                        if (fragment.isAdded) show(fragment)
                        else add(R.id.loginFragmentContainer, fragment, fragment.javaClass.name)
                    }
                    prevPage = page
                }
            }
        }
    }

    private var backButtonPressedOnce = false
    val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (backButtonPressedOnce) finish()
            else {
                backButtonPressedOnce = true
                showToast("한 번 더 누르면 종료됩니다")
                lifecycleScope.launch {
                    delay(2000)
                    backButtonPressedOnce = false
                }
            }
        }
    }

    private fun setBackBtn() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}