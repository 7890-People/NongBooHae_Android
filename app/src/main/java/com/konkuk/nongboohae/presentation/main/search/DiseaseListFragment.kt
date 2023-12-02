package com.konkuk.nongboohae.presentation.main.search

import BaseFragment
import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchView
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentDiseaseListBinding
import com.konkuk.nongboohae.presentation.main.MainActivity
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiseaseListFragment : BaseFragment<FragmentDiseaseListBinding>() {
    override val TAG: String = "DiseaseListFragment"
    override val layoutRes: Int = R.layout.fragment_disease_list
    val viewModel: SearchViewModel by activityViewModels()

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

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.diseaseListResponse.observeLiveData(
            onSuccess = {
                diseaseListItemAdapter.diseaseList = it.diseases
                diseaseListItemAdapter.notifyDataSetChanged()

                searchItemAdapter.diseaseList = it.diseases
                searchItemAdapter.notifyDataSetChanged()
            },
            onFailure = {
                showToast("데이터 로딩 실패")
                //TODO: 다시 요청

            }
        )
    }

    private fun initData() {
        val job = viewModel.requestDiseaseListAsync(category = "all")
        lifecycleScope.launch(Dispatchers.IO) {
            //처음 한번 기다리고 viewModel에 저장
            job.await().byState(
                onSuccess = { viewModel.entireList = it.diseases }
            )
        }
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
                activity.setBtnvVisibility(false)
                activityDispatcher.addCallback(onSearchBackPressedCallback)
            } else if (newState == SearchView.TransitionState.HIDING) {
                activity.setBtnvVisibility(true)
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
                val filteredList = viewModel.entireList.filter { disease ->
                    disease.korName.contains(editText.text.toString())
                }
                searchItemAdapter.diseaseList = filteredList
                searchItemAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setCategoryListener() {

    }

}