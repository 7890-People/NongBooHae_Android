package com.konkuk.nongboohae.presentation.diagnosis

import com.bumptech.glide.Glide
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisImageFullScreenBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity

class DiagnosisImageFullScreenActivity : BaseActivity<ActivityDiagnosisImageFullScreenBinding>() {
    override val TAG: String = "DiagnosisImageFullScreenActivity"
    override val layoutRes: Int = R.layout.activity_diagnosis_image_full_screen
    lateinit var viewModel: DiagnosisResultViewModel

    override fun initViewModel() {

    }

    override fun afterViewCreated() {
        val photoUrl = intent.getStringExtra("photoUri")
        Glide.with(this)
            .load(photoUrl)
            .into(binding.fullScreenIv)
        initClickListener()
    }

    private fun initClickListener() {
        binding.exitIv.setOnClickListener {
            finish()
        }
    }

}