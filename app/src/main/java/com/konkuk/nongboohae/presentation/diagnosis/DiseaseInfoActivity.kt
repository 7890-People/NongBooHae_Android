package com.konkuk.nongboohae.presentation.diagnosis

import androidx.lifecycle.ViewModelProvider
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiseaseInfoBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.util.factory.ViewModelFactory

class DiseaseInfoActivity : BaseActivity<ActivityDiseaseInfoBinding>() {
    override val TAG: String = "DiseaseInfoActivity"
    override val layoutRes: Int = R.layout.activity_disease_info
    lateinit var viewModel: DiseaseInfoViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(DiseaseRepository()))[DiseaseInfoViewModel::class.java]
    }

    override fun afterViewCreated() {
        initClickListener()
    }

    private fun initClickListener() {
        binding.diseaseInfoExitIv.setOnClickListener {
            finish()
        }
    }
}