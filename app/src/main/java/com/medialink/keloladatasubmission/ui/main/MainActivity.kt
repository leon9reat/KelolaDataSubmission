package com.medialink.keloladatasubmission.ui.main

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.databinding.ActivityMainBinding
import com.medialink.keloladatasubmission.ui.fragment.BaseFragment
import com.medialink.keloladatasubmission.utils.AppConfig

class MainActivity : AppCompatActivity(), IMainActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        with(binding) {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(binding.viewPager)
        }
        supportActionBar?.elevation = 0f
    }

    override fun getCount(): Int = TAB_TITLE.size

    override fun getFragment(position: Int): Fragment {
        return when (position) {
            0 -> BaseFragment.newInstance(AppConfig.TYPE_MOVIE)
            1 -> BaseFragment.newInstance(AppConfig.TYPE_TV_SHOW)
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence =
        resources.getString(TAB_TITLE[position])

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
    }
}