package com.konkuk.nongboohae.presentation.diagnosis

import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiseaseInfoBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
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
        initData()
    }

    private fun initData() {
        val plantName = intent.getStringExtra("plantName")
        val diagnosisResultResponse = intent.getSerializableExtra("diagnosisResultResponse") as DiagnosisResultResponse
        binding.diseaseInfoCropNameTv.text = plantName
        binding.diseaseInfoDxNameTv.text = diagnosisResultResponse.diseaseName
        binding.diseaseInfoEnvContentTv.text = diagnosisResultResponse.condition
        binding.diseaseInfoSymptomContentTv.text = diagnosisResultResponse.symptoms
        binding.diseaseInfoTreatmentContentTv.text = diagnosisResultResponse.preventionMethod
        Glide.with(this).load(diagnosisResultResponse.diseaseImg).into(binding.diseaseInfoPhotoIv)
    }

    private fun initClickListener() {
        binding.diseaseInfoExitIv.setOnClickListener {
            finish()
        }
    }
}