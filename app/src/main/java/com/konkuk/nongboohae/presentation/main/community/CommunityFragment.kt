package com.konkuk.nongboohae.presentation.main.community

import BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.konkuk.mocacong.presentation.main.MainViewModel
import com.konkuk.nongboohae.R
import com.konkuk.nongboohae.databinding.FragmentCommunityBinding

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    override val TAG: String
        get() = "Community"
    override val layoutRes: Int
        get() = R.layout.fragment_community

    val mainViewModel: MainViewModel by activityViewModels()

    override fun afterViewCreated() {
        setTabViewPager()
    }


    private fun setTabViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0->{
                    tab.text = "  자유게시판  "
                }
                1->{
                    tab.text = "  질문게시판  "
                }
            }
        }.attach()
    }


    class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val NUM_TABS = 2

        override fun getItemCount(): Int {
            return NUM_TABS
        }

        override fun createFragment(position: Int): Fragment {
            val fragment = PostListFragment()
            val bundle = Bundle()
            when (position) {
                0 -> {
                    bundle.putString("boardType", "free")
                }
                1 -> {
                    bundle.putString("boardType", "qna")
                }
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
