package com.konkuk.nongboohae.presentation.diagnosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.ActivityDiagnosisResultBinding
import com.konkuk.nongboohae.presentation.base.BaseActivity

class DiagnosisResultActivity : BaseActivity<ActivityDiagnosisResultBinding>() {
    override val TAG: String = "DiagnosisResultActivity"
    override val layoutRes: Int = R.layout.activity_diagnosis_result
    lateinit var viewModel: DiagnosisResultViewModel

    override fun initViewModel() {
        viewModel = createViewModel(DiagnosisRepository())
    }

    override fun afterViewCreated() {

    }
}