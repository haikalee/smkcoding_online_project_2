package com.haikal.project2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.haikal.project2.fragment.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 5
    }
    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> { return Dashboard() }
            1 -> { return PengertianFragment() }
            2 -> { return PencegahanFragment() }
            3 -> { return PengobatanFragment() }
            4 -> { return NotepadFragment() }
            else -> { return Dashboard() }
        }
    }
}