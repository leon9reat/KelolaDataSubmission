package com.medialink.keloladatasubmission.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.databinding.ActivityDetailBinding
import com.medialink.keloladatasubmission.ui.detail.movie.MovieDetFragment
import com.medialink.keloladatasubmission.ui.detail.tv.TvDetFragment
import com.medialink.keloladatasubmission.utils.AppConfig

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var mType: Int = 0
    private var mId: Int = 0

    companion object {
        const val ID_JENIS = "id_jenis"
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mType = intent.getIntExtra(ID_JENIS, 0)
        mId = intent.getIntExtra(ID, 0)

        if (mType == AppConfig.TYPE_MOVIE) {
            val movieDetailsFragment = MovieDetFragment.newInstance(mId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_container, movieDetailsFragment)
                .addToBackStack(null)
                .commit()
        } else if (mType == AppConfig.TYPE_TV_SHOW) {
            val tvDetailFrag = TvDetFragment.newInstance(mId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_container, tvDetailFrag)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return true
    }

    override fun onBackPressed() {
        this.finish()
    }
}