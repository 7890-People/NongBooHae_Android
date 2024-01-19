package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisResultBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.util.factory.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DiagnosisResultActivity : BaseActivity<ActivityDiagnosisResultBinding>() {
    override val TAG: String = "DiagnosisResultActivity"
    override val layoutRes: Int = R.layout.activity_diagnosis_result
    lateinit var viewModel: DiagnosisResultViewModel
    lateinit var photoUri : String
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
        viewModel.diagnosisResultResponse.observe(this){
            binding.diagnosisResultDiseaseTv.text = it.diseaseName
            // ..... 이런식으로 하는 거 맞나요?
        }
    }

    private fun initData() {
        photoUri = intent.getStringExtra("photoUri").toString()
        plantName = "포도"
        Glide.with(this)
            .load(photoUri)
            .into(binding.diagnosisResultPhotoIv)
        viewModel.requestDiagnosisResult(photoUri = photoUri, plantName = plantName)
    }

    private fun initClickListener() {
        binding.diagnosisResultDiseaseMoreIv.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            intent.putExtra("diseaseName", binding.diagnosisResultDiseaseTv.text)
            intent.putExtra("imgUrl", "https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3013_0120161027094643027_TMB.jpg")
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