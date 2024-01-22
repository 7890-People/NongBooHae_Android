package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import android.net.Uri
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

            // <br/> 기준으로 나누기 => 문장 앞에 - 삽입, 뒤에 \n 삽입
            val sentences = it.symptoms.split("<br/>")
            val formattedText = StringBuilder()
            for (sentence in sentences) {
                formattedText.append("- ").append(sentence.trim()).append("\n")
            }
            val symptoms = formattedText.toString()
            binding.diagnosisResultSymptomContentTv.text = symptoms

        }
    }

    private fun initData() {
        photoUri = intent.getStringExtra("photoUri")!!
        val filePath = intent.getStringExtra("currentPhotoPath")!!
        plantName = intent.getStringExtra("plantName")!!
        Glide.with(this)
            .load(photoUri)
            .into(binding.diagnosisResultPhotoIv)
        viewModel.requestDiagnosisResult(filePath = filePath, plantName = plantName)
    }

    private fun initClickListener() {
        binding.diagnosisResultDiseaseMoreIv.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("diseaseName", binding.diagnosisResultDiseaseTv.text)
            intent.putExtra(
                "imgUrl",
                "https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3013_0120161027094643027_TMB.jpg"
            )
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