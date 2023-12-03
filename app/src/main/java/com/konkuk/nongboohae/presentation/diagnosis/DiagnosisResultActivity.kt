package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
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
    lateinit var photoUri : String

    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DiagnosisRepository())
        )[DiagnosisResultViewModel::class.java]
    }

    override fun afterViewCreated() {
        initData()
        initClickListener()
    }

    private fun initData() {
        photoUri = intent.getStringExtra("photoUri").toString()
        Glide.with(this)
            .load(photoUri)
            .into(binding.diagnosisResultPhotoIv)
        binding.diagnosisResultDis1ProgressBar.progress = 78
        binding.diagnosisResultDis2ProgressBar.progress = 21
        binding.diagnosisResultDis3ProgressBar.progress = 5
    }

    private fun initClickListener() {
        binding.diagnosisResultDis1Name.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("diseaseName", binding.diagnosisResultDis1NameTv.text)
            intent.putExtra("imgUrl", "https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3013_0120161027094643027_TMB.jpg")
            startActivity(intent)
        }
        binding.diagnosisResultDis2Name.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("diseaseName", binding.diagnosisResultDis2NameTv.text)
            intent.putExtra("imgUrl", "https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3016_0120161027095733733_TMB.jpg")
            startActivity(intent)
        }
        binding.diagnosisResultDis3Name.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("diseaseName", binding.diagnosisResultDis3NameTv.text)
            intent.putExtra("imgUrl", "")
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