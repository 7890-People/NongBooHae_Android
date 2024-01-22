package com.konkuk.nongboohae.presentation.diagnosis.camera

import BaseFragment
import android.content.Intent
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentCheckPhotoBinding
import com.konkuk.nongboohae.presentation.diagnosis.DiagnosisResultActivity
import com.konkuk.nongboohae.util.setOnSingleClickListener

class CheckPhotoFragment : BaseFragment<FragmentCheckPhotoBinding>() {
    override val TAG: String = "FragmentCheckPhoto"
    override val layoutRes: Int = R.layout.fragment_check_photo

    private lateinit var currentPhotoPath: String
    private lateinit var uri: String
    private lateinit var plantName: String

    override fun afterViewCreated() {
        arguments?.apply {
            uri = getString("photoUri")!!
            currentPhotoPath = getString("currentPhotoPath")!!
            plantName = getString("plantName")!!
        }

        binding.imageView.clipToOutline = true
        Glide.with(requireActivity())
            .load(uri)
            .into(binding.imageView)

        binding.completeBtn.setOnSingleClickListener {
            gotoResultActivity()
        }
        binding.retakeBtn.setOnSingleClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun gotoResultActivity() {
        val intent = Intent(requireActivity(), DiagnosisResultActivity::class.java)
        intent.putExtra("photoUri", uri)
        intent.putExtra("currentPhotoPath", currentPhotoPath)
        intent.putExtra("plantName", plantName)
        startActivity(intent)
        requireActivity().finish()
    }
}