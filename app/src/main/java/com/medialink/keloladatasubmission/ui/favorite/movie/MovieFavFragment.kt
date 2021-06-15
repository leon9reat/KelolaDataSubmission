package com.medialink.keloladatasubmission.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.databinding.FragmentBaseBinding
import com.medialink.keloladatasubmission.ui.detail.DetailActivity
import com.medialink.keloladatasubmission.ui.fragment.movie.MovieAdapter
import com.medialink.keloladatasubmission.ui.fragment.movie.MovieViewModel
import com.medialink.keloladatasubmission.utils.AppConfig
import com.medialink.keloladatasubmission.viewmodel.ViewModelFactory
import com.medialink.keloladatasubmission.vo.Status

class MovieFavFragment : Fragment(), IMovieFavFargment {

    private val TAG = MovieFavFragment::class.java.simpleName
    private lateinit var binding: FragmentBaseBinding
    private lateinit var viewModel: MovieFavViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavViewModel::class.java]

            val movieAdapter = MovieFavAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getListMovie().observe(viewLifecycleOwner, { listMovie ->
                binding.progressBar.visibility = View.GONE
                movieAdapter.submitList(listMovie)
            })

            with(binding.moviesRv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun movieDeleteClick(movie: MovieDetailEntity) {
        viewModel.setFavoriteMovie(movie)
        val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.message_ok) { v ->
            viewModel.setFavoriteMovie(movie)
        }
        snackbar.show()
    }

    override fun movieDetail(id: Int) {
        val i = Intent(activity, DetailActivity::class.java)
        i.putExtra(DetailActivity.ID, id)
        i.putExtra(DetailActivity.ID_JENIS, AppConfig.TYPE_MOVIE)
        startActivity(i)
    }

}