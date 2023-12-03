package com.konkuk.nongboohae.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.konkuk.nongboohae.databinding.FragmentJoinNicknameBinding
import com.konkuk.nongboohae.util.Extensions.Companion.safeNavigate

class JoinFragment1 : Fragment() {

    private var _binding: FragmentJoinNicknameBinding? = null
    private val binding get() = _binding!!

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
            findNavController().safeNavigate(JoinFragment1Directions.actionJoinFragment1ToJoinFragment2())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}