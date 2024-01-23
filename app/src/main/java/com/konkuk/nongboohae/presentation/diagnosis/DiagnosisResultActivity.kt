package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisResultBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.remote.response.DiagnosisResultResponse
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
        viewModel.diagnosisResultResponse.observe(this) { apiState ->
            apiState.byState(
                onSuccess = {
                    val formattedResponse = DiagnosisResultResponse(
                        it.diseaseName,
                        viewModel.formattingString(it.condition),
                        viewModel.formattingString(it.symptoms),
                        viewModel.formattingString(it.preventionMethod),
                        it.diseaseImg
                    )
                    viewModel.diagnosisResultTemp = formattedResponse

                    binding.loadingLayout.visibility = View.GONE
                    binding.diagnosisResultDiseaseTv.text = formattedResponse.diseaseName
                    binding.diagnosisResultEnvContentTv.text = formattedResponse.condition
                    binding.diagnosisResultSymptomContentTv.text = formattedResponse.symptoms
                    binding.diagnosisResultTreatmentContentTv.text = formattedResponse.preventionMethod
                },
                onFailure = {
                    binding.loadingLayout.visibility = View.GONE
                    showToast("죄송합니다. 진단에 실패했습니다.")
                }
            )
        }
    }

    private fun initData() {
        photoUri = intent.getStringExtra("photoUri")!!
        val filePath = intent.getStringExtra("currentPhotoPath")!!
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
            intent.putExtra("diagnosisResultResponse", viewModel.diagnosisResultTemp)
            startActivity(intent)
        }
        binding.moreCvButton.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("plantName", plantName)
            intent.putExtra("diagnosisResultResponse", viewModel.diagnosisResultTemp)
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