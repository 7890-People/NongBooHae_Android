package com.konkuk.nongboohae.presentation.diagnosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisResultBinding
import com.konkuk.nongboohae.databinding.ActivityDiseaseInfoBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity

class DiseaseInfoActivity : BaseActivity<ActivityDiseaseInfoBinding>() {
    override val TAG: String = "DiseaseInfoActivity"
    override val layoutRes: Int = R.layout.activity_disease_info
    lateinit var viewModel: DiseaseInfoViewModel

    override fun initViewModel() {
        viewModel = createViewModel(DiseaseRepository())
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