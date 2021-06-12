package com.medialink.keloladatasubmission.ui.fragment.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.medialink.keloladatasubmission.R
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity
import com.medialink.keloladatasubmission.data.source.remote.retrofit.ApiConfig
import com.medialink.keloladatasubmission.databinding.BaseItemBinding

class TvAdapter(private val mCallback: ITvFragment) :
    PagedListAdapter<TvDetailEntity, TvAdapter.BaseViewHolder>(DIFF_CALLBACK) {

    inner class BaseViewHolder(private val binding: BaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvDetailEntity) {
            with(binding) {
                tvTitleMovieList.text = data.name
                tvDateMovie.text =
                    itemView.resources.getString(R.string.label_airing) + " " + data.firstAirDate
                tvVoteMovie.text = data.voteAverage.toString()
                progressVote.progress = data.voteAverage?.times(10)?.toInt() ?: 0
                tvOverviewMovie.text = data.overview
                /*itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, data.courseId)
                    itemView.context.startActivity(intent)
                }*/
                Glide.with(itemView.context)
                    .load(String.format(ApiConfig.POSTER_PATH, data.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_cloud_download_24)
                            .error(R.drawable.ic_baseline_error_outline_24)
                    )
                    .into(imgPoster)

                if (data.isFavorite) {
                    btnLike.icon = itemView.context.getDrawable(R.drawable.ic_baseline_favorite_24)
                } else {
                    btnLike.icon = itemView.context.getDrawable(R.drawable.ic_baseline_favorite_border_24)
                }
                btnLike.setOnClickListener {
                    mCallback.tvFavoriteClick(data)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = BaseItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BaseViewHolder(binding)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvDetailEntity>() {
            override fun areItemsTheSame(
                oldItem: TvDetailEntity,
                newItem: TvDetailEntity
            ): Boolean {
                return oldItem.id == newItem.id || oldItem.isFavorite == newItem.isFavorite
            }

            override fun areContentsTheSame(
                oldItem: TvDetailEntity,
                newItem: TvDetailEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}


