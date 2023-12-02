package com.konkuk.nongboohae.presentation.main

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityMainBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.presentation.diagnosis.DiagnosisBottomSheet
import com.konkuk.nongboohae.presentation.main.search.SearchRepository
import com.konkuk.nongboohae.presentation.main.search.SearchViewModel
import com.konkuk.nongboohae.util.factory.ViewModelFactory


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val TAG: String = "MainActivity"
    override val layoutRes: Int = R.layout.activity_main
    lateinit var searchViewModel: SearchViewModel

    override fun initViewModel() {
        searchViewModel = ViewModelProvider(
            this, ViewModelFactory(SearchRepository())
        )[SearchViewModel::class.java]
    }

    override fun afterViewCreated() {
        binding.fab.setOnClickListener {
            val modal = DiagnosisBottomSheet()
            modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.TransParentBottomSheetDialogTheme)
            modal.show(supportFragmentManager, DiagnosisBottomSheet.TAG)
        }
    }

    fun setBtnvVisibility(visibility: Boolean) {
        binding.bottomAppBar.visibility = if (visibility) View.VISIBLE else View.GONE
        binding.fab.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}