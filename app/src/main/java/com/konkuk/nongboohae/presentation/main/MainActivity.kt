package com.konkuk.nongboohae.presentation.main

import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.konkuk.mocacong.presentation.main.MainViewModel
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityMainBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.presentation.diagnosis.DiagnosisBottomSheet
import com.konkuk.nongboohae.presentation.main.community.CommunityFragment
import com.konkuk.nongboohae.presentation.main.history.HistoryFragment
import com.konkuk.nongboohae.presentation.main.search.SearchFragment
import com.konkuk.nongboohae.presentation.main.search.SearchRepository
import com.konkuk.nongboohae.presentation.main.search.SearchViewModel
import com.konkuk.nongboohae.util.factory.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val TAG: String = "MainActivity"
    override val layoutRes: Int = R.layout.activity_main
    lateinit var searchViewModel: SearchViewModel
    lateinit var mainViewModel: MainViewModel

    private val searchFragment by lazy {
        supportFragmentManager.findFragmentByTag(SearchFragment::class.java.name) ?: SearchFragment()
    }

    private val historyFragment by lazy {
        supportFragmentManager.findFragmentByTag(HistoryFragment::class.java.name) ?: HistoryFragment()
    }

    private val communityFragment by lazy{
        supportFragmentManager.findFragmentByTag(CommunityFragment::class.java.name) ?: CommunityFragment()
    }

    override fun initViewModel() {
        searchViewModel = ViewModelProvider(
            this, ViewModelFactory(SearchRepository())
        )[SearchViewModel::class.java]
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun afterViewCreated() {
        collectPage()
        binding.fab.setOnClickListener {
            val modal = DiagnosisBottomSheet()
            modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.TransParentBottomSheetDialogTheme)
            modal.show(supportFragmentManager, DiagnosisBottomSheet.TAG)
        }
        setBottomNavi()
        setBackBtn()
    }

    private fun getFragment(page: MainPage): Fragment {
        return when (page) {
            MainPage.SEARCH -> searchFragment
            MainPage.HISTORY -> historyFragment
            MainPage.COMMUNITY -> communityFragment
        }
    }

    private fun collectPage() {
        Log.d(TAG, "collectPage 시작")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                var prevPage = mainViewModel.pageFlow.value
                mainViewModel.pageFlow.collect { page ->
                    Log.d(TAG, " $page collect됨")
                    val preFragment = getFragment(prevPage)
                    val fragment = getFragment(page)
                    supportFragmentManager.commit {
                        if (preFragment != fragment) hide(preFragment)
                        if (fragment.isAdded) {
                            show(fragment)
                        } else add(R.id.mainFragmentContainer, fragment, fragment.javaClass.name)
                    }
                    prevPage = page
                }
            }
        }
    }

    fun setBtnvVisibility(visibility: Boolean) {
        binding.bottomAppBar.visibility = if (visibility) View.VISIBLE else View.GONE
        binding.fab.visibility = if (visibility) View.VISIBLE else View.GONE
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

    private fun setBottomNavi() {
        binding.btnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnv_history -> {
                    mainViewModel.gotoPage(MainPage.HISTORY)
                }
                R.id.btnv_community -> {
                    mainViewModel.gotoPage(MainPage.COMMUNITY)
                }
                R.id.btnv_search -> {
                    mainViewModel.gotoPage(MainPage.SEARCH)
                }
                R.id.btnv_mypage -> {

                }
            }
            return@setOnItemSelectedListener true
        }
    }
}