package com.konkuk.nongboohae.presentation.main

import android.Manifest.permission_group.STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityMainBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.presentation.diagnosis.DiagnosisBottomSheet
import com.konkuk.nongboohae.presentation.main.search.SearchFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val TAG: String = "MainActivity"
    override val layoutRes: Int = R.layout.activity_main

    override fun initViewModel() {
        setBottomNavi()
    }

    override fun afterViewCreated() {
        binding.fab.setOnClickListener {
            val modal = DiagnosisBottomSheet()
            modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.TransParentBottomSheetDialogTheme)
            modal.show(supportFragmentManager, DiagnosisBottomSheet.TAG)
        }
    }


    private fun setBottomNavi() {
        binding.btnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.HistoryFragment -> {
                    setFragment(R.id.nav_host_fragment, SearchFragment())
                    Log.d("Stack-Log", "HistoryFragment()")
                    return@setOnItemSelectedListener true
                }
                R.id.CommunityFragment -> {
                    setFragment(R.id.nav_host_fragment, SearchFragment())
                    Log.d("Stack-Log", "SearchFragment()")
                    return@setOnItemSelectedListener true
                }
                R.id.SearchFragment -> {
                    setFragment(R.id.nav_host_fragment, SearchFragment())
                    Log.d("Stack-Log", "SearchFragment()")
                    return@setOnItemSelectedListener true
                }
                R.id.MyPageFragment -> {
                    setFragment(R.id.nav_host_fragment, SearchFragment())
                    Log.d("Stack-Log", "SearchFragment()")
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}