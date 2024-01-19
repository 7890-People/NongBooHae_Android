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

    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DiagnosisResultRepository())
        )[DiagnosisResultViewModel::class.java]
    }

    override fun afterViewCreated() {
        initData()
        initClickListener()
    }

    private fun initData() {
        photoUri = intent.getStringExtra("photoUri").toString()
        val file = File(photoUri.toString())
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val plantImg = MultipartBody.Part.createFormData("plantImg", file.name, requestFile) //폼데이터
        val plantName: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "포도")
        Glide.with(this)
            .load(photoUri)
            .into(binding.diagnosisResultPhotoIv)

        val job = viewModel.requestDiagnosisResultAsync(plantImg = plantImg, plantName = plantName)
        lifecycleScope.launch(Dispatchers.IO) {
            //처음 한번 기다리고 viewModel에 저장
            job.await().byState(
                onSuccess = { viewModel.diagnosisResultResponse = it }
            )
        }
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