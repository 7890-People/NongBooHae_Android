package com.konkuk.nongboohae.presentation.diagnosis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.DialogDiagnosisBottomSheetBinding

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
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogDiagnosisBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "DiagnosisBottomSheet"
    }
}