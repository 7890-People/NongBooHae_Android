package com.konkuk.nongboohae.presentation.main.history

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.nongboohae.databinding.ItemHistoryBinding

data class History(
    var name: String = "",
    var isExpanded: Boolean = false,
    var image: Int = -1
)

class HistoryRVAapter(private val historyList : ArrayList<History>): RecyclerView.Adapter<HistoryRVAapter.ViewHolder>() {

    private lateinit var mItemClickListener: MyItemClickListener

    class ViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History){
            binding.historyItemDiseaseKr.text = history.name
            binding.historyItemGoBtn.setOnClickListener {
                Log.d("history", "더 보기 버튼 클릭됨")
                ToggleAnimation.toggleArrow(it, !history.isExpanded)
                if (!history.isExpanded) {
                    ToggleAnimation.expand(binding.historyItemHiddenLayout)
                    history.isExpanded = true
                } else {
                    ToggleAnimation.collapse(binding.historyItemHiddenLayout)
                    history.isExpanded = false
                }
            }
        }
    }

    interface MyItemClickListener {
        fun onGoBtnClick(history: History)
    }

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HistoryRVAapter.ViewHolder {
        val binding: ItemHistoryBinding = ItemHistoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyList[position])
    }
}