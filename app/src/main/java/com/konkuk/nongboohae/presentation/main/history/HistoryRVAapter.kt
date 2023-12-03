package com.konkuk.nongboohae.presentation.main.history

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.databinding.ItemHistoryBinding

data class History(
    var imgUrl: String = "",
    var speciesName: String = "",
    var date: String,
    var disease1Name: String = "",
    var disease1Percent: Int = 0,
    var disease2Name: String = "",
    var disease2Percent: Int = 0,
    var isExpanded: Boolean = false
)

class HistoryRVAapter(private val historyList : ArrayList<History>): RecyclerView.Adapter<HistoryRVAapter.ViewHolder>() {

    private lateinit var mItemClickListener: MyItemClickListener

    class ViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History){
            binding.historyItemDiseaseKr.text = history.disease1Name
            binding.historyItemDate.text = history.date
            binding.historyItemCategoryText.text = history.speciesName
            binding.historyItemDis1NameTv.text = history.disease1Name
            binding.historyItemDis1PercentTv.text = history.disease1Percent.toString()+"%"
            binding.historyItemDis2NameTv.text = history.disease2Name
            binding.historyItemDis2PercentTv.text = history.disease2Percent.toString()+"%"
            Glide.with(itemView.context).load(history.imgUrl).into(binding.historyItemPhotoIv)
            Glide.with(itemView.context).load(history.imgUrl).into(binding.historyItemCropImg)
            binding.historyItemDis1ProgressBar.progress = history.disease1Percent
            binding.historyItemDis2ProgressBar.progress = history.disease2Percent

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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHistoryBinding = ItemHistoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyList[position])
    }
}