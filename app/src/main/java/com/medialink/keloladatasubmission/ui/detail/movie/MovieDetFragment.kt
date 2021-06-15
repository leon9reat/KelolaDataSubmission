package com.medialink.keloladatasubmission.ui.detail.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiConfig
import com.medialink.keloladatasubmission.databinding.FragmentMovieDetBinding
import com.medialink.keloladatasubmission.viewmodel.ViewModelFactory
import com.medialink.keloladatasubmission.vo.Status

class MovieDetFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetBinding
    private lateinit var viewModel: MovieDetViewModel
    private var mId: Int = 0
    private val TAG = MovieDetFragment::class.java.simpleName

    companion object {
        private const val PARAMETER: String = "PARAMETER"

        @JvmStatic
        fun newInstance(id: Int) =
            MovieDetFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAMETER, id)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            mId = bundle.getInt(PARAMETER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
        viewModel.getMovie(mId).observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> {
                        Log.d(TAG, "Status.LOADING: ")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> if (movie.data != null) {
                        renderData(movie.data)
                        binding.layoutDetail.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "Status.SUCCESS: ${movie.data}")
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, movie.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Status.ERROR: ${movie.message}")
                    }
                }
            }
        })
    }

    private fun renderData(movie: MovieDetailEntity) {
        Glide.with(requireActivity())
            .load(String.format(ApiConfig.POSTER_PATH, movie.posterPath))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.ivPoster)

        with(binding) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            tvReleaseDate.text = movie.releaseDate
            labelReleaseDate.text = getString(R.string.label_release)
            tvVote.text = movie.voteAverage.toString() + "/ 10"
            labelVote.text = movie.voteCount.toString() + " votes"
            tvLanguage.text = movie.originalLanguage

            if (movie.isFavorite) {
                fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            fabFavorite.setOnClickListener {
                viewModel.setFavoriteMovie(movie)
            }
        }
    }


    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(MovieDetViewModel::class.java)
    }


}