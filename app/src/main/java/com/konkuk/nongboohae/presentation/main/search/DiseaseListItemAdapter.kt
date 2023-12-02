package com.konkuk.nongboohae.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.konkuk.nongboohae.databinding.ItemDiseaseBinding
import com.konkuk.nongboohae.presentation.main.search.model.DiseaseListPresentModel


class DiseaseListItemAdapter(
    val itemClickedListener: (DiseaseListPresentModel) -> Unit
) : RecyclerView.Adapter<DiseaseListItemAdapter.MyViewHolder>() {

    var diseaseList = listOf<DiseaseListPresentModel>()

    inner class MyViewHolder(private val binding: ItemDiseaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disease: DiseaseListPresentModel) {
            binding.cropImg.clipToOutline = true
            binding.disease = disease
            disease.imgUrl?.let {
                Glide.with(binding.root.context).load(it).into(binding.cropImg)
            }
            binding.root.setOnClickListener {
                itemClickedListener(disease)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemDiseaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = diseaseList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(diseaseList[position])
    }

}