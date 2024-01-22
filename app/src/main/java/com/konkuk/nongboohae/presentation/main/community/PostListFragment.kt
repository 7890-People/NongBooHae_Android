package com.konkuk.nongboohae.presentation.main.community

import BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentPostListBinding
import com.konkuk.nongboohae.presentation.model.PostPreviewUiModel
import java.text.SimpleDateFormat
import java.util.*

class PostListFragment : BaseFragment<FragmentPostListBinding>() {
    override val TAG: String
        get() = "Postlist"
    override val layoutRes: Int
        get() = R.layout.fragment_post_list

    override fun afterViewCreated() {
        arguments?.let {
            val type = it.getString("boardType")
            when (type) {
                "free" -> {
                    //viewmodel 데이터 요청
                }
                "qna" -> {
                    //request
                }
            }
        }

        val adapter = PostPreviewAdapter {

        }

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date()).toString()
        adapter.postList = listOf(
            PostPreviewUiModel("토마토", "우리집 토마토에 이상한 증상이 생겼어요", "토마토마토", date, ""),
            PostPreviewUiModel("토마토", "우리집 토마토에 이상한 증상이 생겼어요", "토마토마토", date, ""),
            PostPreviewUiModel("토마토", "우리집 토마토 돼지야", "토마토마토", date, ""),
            PostPreviewUiModel("토마토", "우리집 포도에 이상한 증상이 생겼어요", "토마토마토", date, ""),
            PostPreviewUiModel("토마토", "너 내 포도도도독", "토마토마토", date, "")
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
}