package com.medialink.keloladatasubmission.ui.favorite.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.databinding.FragmentBaseBinding
import com.medialink.keloladatasubmission.ui.detail.DetailActivity
import com.medialink.keloladatasubmission.ui.favorite.movie.MovieFavAdapter
import com.medialink.keloladatasubmission.utils.AppConfig
import com.medialink.keloladatasubmission.viewmodel.ViewModelFactory

class TvFavFragment : Fragment(), ITvFavFragment {

    private val TAG = TvFavFragment::class.java.simpleName
    private lateinit var binding: FragmentBaseBinding
    private lateinit var viewModel: TvFavViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvFavViewModel::class.java]

            val tvAdapter = TvFavAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getListTv().observe(viewLifecycleOwner, { listTv ->
                binding.progressBar.visibility = View.GONE
                tvAdapter.submitList(listTv)
            })

            with(binding.moviesRv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun tvDeleteClick(tv: TvDetailEntity) {
        viewModel.setFavoriteTv(tv)
        val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.message_ok) { v ->
            viewModel.setFavoriteTv(tv)
        }
        snackbar.show()
    }

    override fun tvDetail(id: Int) {
        val i = Intent(activity, DetailActivity::class.java)
        i.putExtra(DetailActivity.ID, id)
        i.putExtra(DetailActivity.ID_JENIS, AppConfig.TYPE_TV_SHOW)
        startActivity(i)
    }
}