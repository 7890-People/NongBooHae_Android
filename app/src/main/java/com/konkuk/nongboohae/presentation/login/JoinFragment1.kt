package com.konkuk.nongboohae.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.konkuk.nongboohae.databinding.FragmentJoinNicknameBinding

class JoinFragment1 : Fragment() {

    private var _binding: FragmentJoinNicknameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinNicknameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        binding.nextBtn.setOnClickListener {
            viewModel.goto(LoginPage.JOIN2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}