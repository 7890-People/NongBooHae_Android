package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisResultBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.util.factory.ViewModelFactory


class DiagnosisResultActivity : BaseActivity<ActivityDiagnosisResultBinding>() {
    override val TAG: String = "DiagnosisResultActivity"
    override val layoutRes: Int = R.layout.activity_diagnosis_result
    lateinit var viewModel: DiagnosisResultViewModel
    lateinit var photoUri: String
    lateinit var plantName: String

    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DiagnosisResultRepository())
        )[DiagnosisResultViewModel::class.java]
    }

    override fun afterViewCreated() {
        initData()
        initObservers()
        initClickListener()
    }

    private fun initObservers() {
        viewModel.diagnosisResultResponse.observe(this) {
            binding.diagnosisResultDiseaseTv.text = it.diseaseName
            binding.diagnosisResultEnvContentTv.text = it.condition
            binding.diagnosisResultSymptomContentTv.text = it.symptoms
            binding.diagnosisResultTreatmentContentTv.text = it.preventionMethod
        }
    }

    private fun initData() {
        photoUri = intent.getStringExtra("photoUri")!!
        val filePath = intent.getStringExtra("currentPhotoPath")
        plantName = intent.getStringExtra("plantName")!!
        Log.d("retrofit-plantName", plantName.toString())
        Glide.with(this)
            .load(photoUri)
            .into(binding.diagnosisResultPhotoIv)
        viewModel.requestDiagnosisResult(filePath = filePath, plantName = plantName)
    }

    private fun initClickListener() {
        binding.diagnosisResultDiseaseMoreIv.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("plantName", plantName)
            intent.putExtra("diagnosisResultResponse", viewModel.diagnosisResultResponse.value)
            startActivity(intent)
        }
        binding.diagnosisResultExitIv.setOnClickListener {
            finish()
        }
        binding.diagnosisResultPhotoIv.setOnClickListener {
            val intent = Intent(this, DiagnosisImageFullScreenActivity::class.java)
            intent.putExtra("photoUri", photoUri)
            startActivity(intent)
        }
    }
}