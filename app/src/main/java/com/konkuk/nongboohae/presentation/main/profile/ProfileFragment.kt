package com.konkuk.nongboohae.presentation.main.profile

import BaseFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentProfileBinding
import com.konkuk.nongboohae.presentation.main.MainActivity
import com.konkuk.nongboohae.presentation.main.MainPage

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

        binding.profileEditIv.setOnClickListener {
            val act = requireActivity() as MainActivity
            val profileEditFragment = ProfileEditFragment()
            startFragment(profileEditFragment, "profileEditFragment")
            act.setBtnvVisibility(false)
        }
    }

    private fun startFragment(fragment: Fragment, name: String){
        val transaction: FragmentTransaction = (context as MainActivity).supportFragmentManager.beginTransaction()
        transaction.addToBackStack(name).replace(R.id.mainFragmentContainer, fragment)
        transaction.commit()
    }

}