package com.konkuk.nongboohae.presentation.diagnosis

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.konkuk.nongboohae.databinding.DialogDiagnosisBottomSheetBinding
import com.konkuk.nongboohae.presentation.diagnosis.camera.CameraActivity

class DiagnosisBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDiagnosisBottomSheetBinding
    lateinit var plantName: String

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
        plantName = binding.diagnosisBottomSheetCrop1Tv.text.toString()
        val intent = Intent(requireContext(), CameraActivity::class.java)
        intent.putExtra("plantName", plantName)
        startActivity(intent)
        dismiss()
    }

}