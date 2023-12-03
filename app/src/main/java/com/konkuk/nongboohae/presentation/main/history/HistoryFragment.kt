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
        histories.add(History("https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3013_0120161027094643027_TMB.jpg","포도", "2023-12-01","갈색무늬병", 78, "그을음점무늬병",17))
        histories.add(History("https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3005_0120161027100845296_TMB.jpg","포도", "2023-12-02","꼭지마름병", 81, "그을음점무늬병",21))
        histories.add(History("https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3016_0120161027095733733_TMB.jpg","포도", "2023-12-02","그을음점무늬병", 65, "갈색무늬병",20))
        histories.add(History("https://www.nongsaro.go.kr/portal/imgView.do?filePath=/npms/photo/sickns2/&fileName=img_3002_0120161027104815284_TMB.jpg","포도", "2023-12-03","먼지곰팡이병", 78, "흰가루병",27))
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