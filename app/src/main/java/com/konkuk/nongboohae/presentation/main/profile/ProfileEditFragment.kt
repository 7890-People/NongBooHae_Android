package com.konkuk.nongboohae.presentation.main.profile

import BaseFragment
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentProfileEditBinding
import com.konkuk.nongboohae.presentation.main.MainActivity

class ProfileEditFragment : BaseFragment<FragmentProfileEditBinding>() {
    override val TAG: String = "ProfileEditFragment"
    override val layoutRes: Int = R.layout.fragment_profile_edit

    override fun afterViewCreated() {
        initClickListener()
    }

    private fun initClickListener() {
        binding.profileEditExitIv.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val act = requireActivity() as MainActivity
        act.setBtnvVisibility(true)
    }

}