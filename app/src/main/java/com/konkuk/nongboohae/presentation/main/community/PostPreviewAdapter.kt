package com.konkuk.nongboohae.presentation.main.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.nongboohae.databinding.ItemPostPreviewBinding
import com.konkuk.nongboohae.presentation.model.PostPreviewUiModel

class PostPreviewAdapter(
    val itemClickedListener: (PostPreviewUiModel) -> Unit
) : RecyclerView.Adapter<PostPreviewAdapter.MyViewHolder>() {

    var postList = listOf<PostPreviewUiModel>()

    inner class MyViewHolder(private val binding: ItemPostPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostPreviewUiModel) {
            binding.model = post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemPostPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(postList[position])
    }

}