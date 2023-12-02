package com.konkuk.nongboohae.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.nongboohae.databinding.ItemSearchDiseaseBinding
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel


class SearchItemAdapter(
    val itemClickedListener: (DiseaseListPresentModel) -> Unit
) : RecyclerView.Adapter<SearchItemAdapter.MyViewHolder>() {

    var diseaseList = listOf<DiseaseListPresentModel>()

    inner class MyViewHolder(private val binding: ItemSearchDiseaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disease: DiseaseListPresentModel) {
            binding.disease = disease
            binding.root.setOnClickListener {
                itemClickedListener(disease)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemSearchDiseaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = diseaseList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(diseaseList[position])
    }

}