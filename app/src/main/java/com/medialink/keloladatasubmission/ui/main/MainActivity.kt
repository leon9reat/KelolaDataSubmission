package com.medialink.keloladatasubmission.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.databinding.ActivityMainBinding
import com.medialink.keloladatasubmission.ui.favorite.FavoriteActivity
import com.medialink.keloladatasubmission.ui.fragment.movie.MovieFragment
import com.medialink.keloladatasubmission.ui.fragment.tv.TvFragment
import com.medialink.keloladatasubmission.utils.AppConfig.TAB_TITLE

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
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence =
        resources.getString(TAB_TITLE[position])

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {

            val i = Intent(this, FavoriteActivity::class.java)
            //i.putExtra(DetailActivity.PARCEL_DETAIL, detail)
            startActivity(i)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

}