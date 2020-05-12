package com.haikal.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.haikal.project2.fragment.PencegahanFragment
import com.haikal.project2.fragment.PengertianFragment
import com.haikal.project2.fragment.PengobatanFragment
import com.haikal.project2.fragment.PenyebaranFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val menuText = arrayOf("Penyebaran", "Pengertian", "Pencegahan", "Pengobatan")
    val menuIcon = arrayOf(R.drawable.ic_virus, R.drawable.ic_bacteria, R.drawable.ic_cleaning, R.drawable.ic_healthcare)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ViewPagerAdapter(this)
        view_pager.adapter = adapter
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = menuText[position]
                tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
            }).attach()

    }
}
