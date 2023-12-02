package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisResultBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity
import com.konkuk.nongboohae.presentation.login.LoginViewModel
import com.konkuk.nongboohae.presentation.login.MemberRepository
import com.konkuk.nongboohae.util.factory.ViewModelFactory

class DiagnosisResultActivity : BaseActivity<ActivityDiagnosisResultBinding>() {
    override val TAG: String = "DiagnosisResultActivity"
    override val layoutRes: Int = R.layout.activity_diagnosis_result
    lateinit var viewModel: DiagnosisResultViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(DiagnosisRepository()))[DiagnosisResultViewModel::class.java]
    }

    override fun afterViewCreated() {
        initClickListener()
    }

    private fun initClickListener() {
        binding.diagnosisResultDis1Name.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            startActivity(intent)
        }
        binding.diagnosisResultDis2Name.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            startActivity(intent)
        }
        binding.diagnosisResultDis3Name.setOnClickListener {
            val intent = Intent(this, DiseaseInfoActivity::class.java)
            startActivity(intent)
        }
        binding.diagnosisResultExitIv.setOnClickListener {
            finish()
        }
    }
}