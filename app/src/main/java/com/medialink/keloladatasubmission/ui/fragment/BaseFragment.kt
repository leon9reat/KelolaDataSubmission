package com.medialink.keloladatasubmission.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.databinding.FragmentBaseBinding
import com.medialink.keloladatasubmission.utils.AppConfig
import com.medialink.keloladatasubmission.viewmodel.ViewModelFactory
import com.medialink.keloladatasubmission.vo.Status

class BaseFragment : Fragment(), IBaseFragment {

    private lateinit var binding: FragmentBaseBinding
    private var mIdJenis: Int = 0
    private lateinit var mMovieViewModel: MovieViewModel
    private lateinit var mTvViewModel: TvViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mIdJenis = it.getInt(PARAMETER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            when (mIdJenis) {
                AppConfig.TYPE_MOVIE -> {
                    mMovieViewModel = setupMovieViewModel()
                    val mAdapter = MovieAdapter(this)
                    setupMovieUI(mAdapter)
                    setupMovieObserver(mMovieViewModel, mAdapter)
                }
                AppConfig.TYPE_TV_SHOW -> {
                    /*mTvViewModel  = setupTvViewModel()
                    val mAdapter = TvAdapter(this)
                    setupTvUI(mAdapter)
                    setupTvObserver(mTvViewModel, mAdapter)*/
                }
            }
        }
    }

    private fun setupTvViewModel(): TvViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(this, factory)[TvViewModel::class.java]
    }

    private fun setupTvUI(tvAdapter: TvAdapter) {
        binding.moviesRv.adapter = tvAdapter

        with(binding.moviesRv) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            setHasFixedSize(true)
        }
    }

    private fun setupTvObserver(viewModel: TvViewModel, adapter: TvAdapter) {
        viewModel.getListTv().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    Log.d("TAG", "Status.LOADING: ")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    Log.d("TAG", "Status.SUCCESS: ")
                    it.data?.let { listData ->
                        if (listData.isNotEmpty()) {
                            binding.moviesRv.visibility = View.VISIBLE
                            adapter.submitList(it.data)
                        } else {
                            binding.moviesRv.visibility = View.GONE
                        }
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Log.d("TAG", "Status.ERROR: ")
                    binding.moviesRv.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupMovieViewModel(): MovieViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity())
        return ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    private fun setupMovieUI(movieAdapter: MovieAdapter) {
        binding.moviesRv.adapter = movieAdapter

        with(binding.moviesRv) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            setHasFixedSize(true)
        }
    }

    private fun setupMovieObserver(viewModel: MovieViewModel, adapter: MovieAdapter) {
        viewModel.getListMovie().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    Log.d("TAG", "Status.LOADING: ")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    Log.d("TAG", "Status.SUCCESS: ")
                    it.data?.let { listData ->
                        if (listData.isNotEmpty()) {
                            binding.moviesRv.visibility = View.VISIBLE
                            adapter.submitList(it.data)
                        } else {
                            binding.moviesRv.visibility = View.GONE
                        }
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Log.d("TAG", "Status.ERROR: ")
                    binding.moviesRv.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        const val PARAMETER: String = "PARAMETER"

        @JvmStatic
        fun newInstance(idJenis: Int) =
            BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAMETER, idJenis)
                }
            }
    }

    override fun onItemClick(id: Int, idType: Int) {
        TODO("onItemClick")
    }

    override fun movieFavoriteClick(movie: MovieDetailEntity) {
        Log.d("debug", "movieFavoriteClick : ${movie.title}")
        mMovieViewModel.setFavoriteMovie(movie)
    }

    override fun tvFavoriteClick(tv: TvDetailEntity) {
        Log.d("debug", "tvFavoriteClick :")
    }


}