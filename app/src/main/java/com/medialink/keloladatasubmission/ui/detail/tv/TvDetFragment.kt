package com.medialink.keloladatasubmission.ui.detail.tv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiConfig
import com.medialink.keloladatasubmission.databinding.FragmentMovieDetBinding
import com.medialink.keloladatasubmission.ui.detail.movie.MovieDetFragment
import com.medialink.keloladatasubmission.ui.detail.movie.MovieDetViewModel
import com.medialink.keloladatasubmission.viewmodel.ViewModelFactory
import com.medialink.keloladatasubmission.vo.Status

class TvDetFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetBinding
    private lateinit var viewModel: TvDetViewModel
    private var mId: Int = 0
    private val TAG = TvDetFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            mId = bundle.getInt(PARAMETER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setupViewModel()
            setupObserver()
        }
    }

    private fun setupObserver() {
        viewModel.getTv(mId).observe(viewLifecycleOwner, { tv ->
            if (tv != null) {
                when (tv.status) {
                    Status.LOADING -> {
                        Log.d(TAG, "Status.LOADING: ")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> if (tv.data != null) {
                        renderData(tv.data)
                        binding.layoutDetail.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "Status.SUCCESS: ${tv.data}")
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, tv.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Status.ERROR: ${tv.message}")
                    }
                }
            }
        })
    }

    private fun renderData(tv: TvDetailEntity) {
        Glide.with(requireActivity())
            .load(String.format(ApiConfig.POSTER_PATH, tv.posterPath))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.ivPoster)

        with(binding) {
            tvTitle.text = tv.name
            tvOverview.text = tv.overview

            tvReleaseDate.text = tv.firstAirDate
            labelReleaseDate.text = getString(R.string.label_airing)
            tvVote.text = tv.voteAverage.toString() + "/ 10"
            labelVote.text = tv.voteCount.toString() + " votes"
            tvLanguage.text = tv.originalLanguage

            if (tv.isFavorite) {
                fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            fabFavorite.setOnClickListener {
                viewModel.setFavoriteTv(tv)
            }
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(TvDetViewModel::class.java)
    }

    companion object {
        private const val PARAMETER: String = "PARAMETER"

        @JvmStatic
        fun newInstance(id: Int) =
            TvDetFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAMETER, id)
                }
            }
    }
}