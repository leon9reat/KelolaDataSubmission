package com.medialink.keloladatasubmission.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.databinding.ActivityMainBinding
import com.medialink.keloladatasubmission.ui.main.IMainActivity
import com.medialink.keloladatasubmission.ui.main.SectionsPagerAdapter
import com.medialink.keloladatasubmission.utils.AppConfig

class FavoriteActivity : AppCompatActivity(), IMainActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.title_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        with(binding) {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(binding.viewPager)
        }
        supportActionBar?.elevation = 0f
    }

    override fun getCount(): Int = AppConfig.TAB_TITLE.size

    override fun getFragment(position: Int): Fragment {
        return when (position) {
            //0 -> MovieFragment()
            //1 -> TvFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence =
        resources.getString(AppConfig.TAB_TITLE[position])

}