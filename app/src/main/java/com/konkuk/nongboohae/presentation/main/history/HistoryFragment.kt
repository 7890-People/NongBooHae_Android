package com.konkuk.nongboohae.presentation.main.history

import BaseFragment
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    var histories = ArrayList<History>()

    override val TAG: String
        get() = "HistoryFragment"
    override val layoutRes: Int
        get() = R.layout.fragment_history

    override fun afterViewCreated() {
        initRVAdapert()
    }

    private fun initRVAdapert() {
        histories.add(History("~~병",false, 0))
        histories.add(History("병1",false, 0))
        histories.add(History("병2",false, 0))
        histories.add(History("병3",false, 0))
        histories.add(History("병4",false, 0))
        val historyRVAapter = HistoryRVAapter(histories)
        historyRVAapter.setMyItemClickListener(object : HistoryRVAapter.MyItemClickListener{
            override fun onGoBtnClick(history: History) {
                //
            }
        })
        binding.historyRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.historyRv.adapter = historyRVAapter
    }
}