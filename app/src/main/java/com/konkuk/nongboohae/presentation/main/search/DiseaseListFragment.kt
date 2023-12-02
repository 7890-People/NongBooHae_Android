package com.konkuk.nongboohae.presentation.main.search

import BaseFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.search.SearchView
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentDiseaseListBinding
import com.konkuk.nongboohae.presentation.main.MainActivity

class DiseaseListFragment : BaseFragment<FragmentDiseaseListBinding>() {
    override val TAG: String = "DiseaseListFragment"
    override val layoutRes: Int = R.layout.fragment_disease_list
    val viewModel: SearchViewModel by activityViewModels()

    override fun afterViewCreated() {
        initRecyclerViews()
        initLayouts()
        initObservers()
    }

    private fun initObservers() {

    }

    private fun initLayouts() {
        binding.searchView.addTransitionListener { searchView, previousState, newState ->
            val activity = requireActivity() as MainActivity
            if (newState == SearchView.TransitionState.SHOWING) {
                activity.setBtnvVisibility(false)
            } else if (newState == SearchView.TransitionState.HIDING) {
                activity.setBtnvVisibility(true)
            }
        }
    }

    private fun initRecyclerViews() {

    }
}