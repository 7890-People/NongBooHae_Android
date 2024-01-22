package com.konkuk.nongboohae.presentation.diagnosis.camera

import BaseFragment
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentCheckPhotoBinding

class CheckPhotoFragment: BaseFragment<FragmentCheckPhotoBinding>() {
    override val TAG: String = "FragmentCheckPhoto"
    override val layoutRes: Int = R.layout.fragment_check_photo

    override fun afterViewCreated() {
        binding.imageView.clipToOutline = true

    }
}