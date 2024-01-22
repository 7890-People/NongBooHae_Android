package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.konkuk.nongboohae.databinding.DialogDiagnosisBottomSheetBinding
import com.konkuk.nongboohae.presentation.diagnosis.camera.CameraActivity

/*
액티비티에서 다음과 같이 호출하여 사용가능
val modal = DiagnosisBottomSheet()
modal.setStyle(DialogFragment.STYLE_NORMAL, R.style.TransParentBottomSheetDialogTheme)
modal.show(supportFragmentManager, DiagnosisBottomSheet.TAG)
*/

class DiagnosisBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDiagnosisBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogDiagnosisBottomSheetBinding.inflate(inflater, container, false)
        afterBinding()
        return binding.root
    }

    private fun afterBinding() {
        Glide.with(requireActivity())
            .load("https://www.farmnmarket.com/data/photos/20220831/art_16598729535107_00d7c4.jpg")
            .into(binding.diagnosisBottomSheetCrop1Iv)
        Glide.with(requireActivity())
            .load("https://image.yes24.com/images/chyes24/d/1/c/1/d1c1ec15e22d9ed5b88a054464886774.jpg")
            .into(binding.diagnosisBottomSheetCrop2Iv)
        Glide.with(requireActivity())
            .load("https://www.amnews.co.kr/news/photo/202011/44247_31347_2423.jpg")
            .into(binding.diagnosisBottomSheetCrop3Iv)
        binding.diagnosisBottomSheetCrop1Cv.setOnClickListener {
            callCamera()
        }
        binding.diagnosisBottomSheetCrop2Cv.setOnClickListener {
            callCamera()
        }
        binding.diagnosisBottomSheetCrop3Cv.setOnClickListener {
            callCamera()
        }
    }

    companion object {
        const val TAG = "DiagnosisBottomSheet"
    }

    private fun callCamera() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        startActivity(intent)
        dismiss()
    }


}