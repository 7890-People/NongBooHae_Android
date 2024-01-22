package com.konkuk.nongboohae.presentation.main.profile

import BaseFragment
import android.content.Intent
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentProfileBinding

class ProfileFragment: BaseFragment<FragmentProfileBinding>() {
    override val TAG: String = "ProfileFragment"
    override val layoutRes: Int = R.layout.fragment_profile
    override fun afterViewCreated() {
        initClickListener()
    }

    private fun initClickListener() {
        binding.profileSettingLayout.setOnClickListener {
            val intent = Intent(requireContext(), ProfileSettingActivity::class.java)
            startActivity(intent)
        }
    }

}