package com.konkuk.nongboohae.presentation.main.search

import BaseFragment
import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchView
import com.konkuk.mocacong.presentation.main.MainViewModel
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentSearchBinding
import com.konkuk.nongboohae.presentation.main.MainActivity
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val TAG: String = "DiseaseListFragment"
    override val layoutRes: Int = R.layout.fragment_search
    val searchViewModel: SearchViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    lateinit var diseaseListItemAdapter: DiseaseListItemAdapter
    lateinit var searchItemAdapter: SearchItemAdapter


    override fun afterViewCreated() {
        initLayouts()
        initRecyclerViews()
        initData()
        initObservers()
    }

    private fun initLayouts() {
        setSearchViewTransitionListener()
        setSearchViewTextAction()
    }

    private fun initRecyclerViews() {
        diseaseListItemAdapter = DiseaseListItemAdapter(onDiseaseItemClicked)
        binding.imageRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.imageRecyclerView.adapter = diseaseListItemAdapter

        searchItemAdapter = SearchItemAdapter(onDiseaseItemClicked)
        binding.searchedRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.searchedRecyclerView.adapter = searchItemAdapter
    }

    private fun initObservers() {
        searchViewModel.diseaseList.observe(this) {
            diseaseListItemAdapter.diseaseList = it
            diseaseListItemAdapter.notifyDataSetChanged()

            searchItemAdapter.diseaseList = it
            searchItemAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        searchViewModel.requestDiseaseList(category = "all")
    }


    private val onDiseaseItemClicked: (disease: DiseaseListPresentModel) -> Unit = {
        //TODO: GOTO Disease Info Activity
    }

    private fun setSearchViewTransitionListener() {
        val onSearchBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.searchView.handleBackInvoked()
            }
        }

        val activityDispatcher = requireActivity().onBackPressedDispatcher

        binding.searchView.addTransitionListener { searchView, previousState, newState ->
            val activity = requireActivity() as MainActivity
            if (newState == SearchView.TransitionState.SHOWING) {
                //TODO: 작물: ㅇㅇ searchPrefixText
                mainViewModel.setBtnvVisibility(false)
                activityDispatcher.addCallback(onSearchBackPressedCallback)
            } else if (newState == SearchView.TransitionState.HIDING) {
                mainViewModel.setBtnvVisibility(true)
                activityDispatcher.addCallback(activity.onBackPressedCallback)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setSearchViewTextAction() {
        val editText = binding.searchView.editText
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val filteredList = searchViewModel.entireList.filter { disease ->
                    disease.korName.contains(editText.text.toString())
                }
                searchItemAdapter.diseaseList = filteredList
                searchItemAdapter.notifyDataSetChanged()
            }
        })
    }

}