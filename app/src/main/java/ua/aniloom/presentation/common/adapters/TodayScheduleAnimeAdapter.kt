package ua.aniloom.presentation.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewListTodayScheduleItemBinding
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.presentation.common.base.BaseDiffUtilItemCallback
import ua.aniloom.presentation.common.utils.extensions.formatToString

class TodayScheduleAnimeAdapter(
    private val onClickListener: (AnimePreview) -> Unit
): PagingDataAdapter<AnimePreview, TodayScheduleAnimeAdapter.TodayScheduleAnimeVH>(
    BaseDiffUtilItemCallback()
) {
    inner class TodayScheduleAnimeVH(private val binding: ViewListTodayScheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: AnimePreview
        ) {
            val epsText = if (item.numEps == 0) "-" else item.numEps.formatToString()
            with(binding) {
                tvName.text = item.title
                tvYear.text = item.aired.year.formatToString()
                tvRating.text = item.score.score.formatToString()
                tvEps.text = itemView.context.getString(R.string.episodes_count, epsText)
                Glide.with(itemView.context)
                    .load(item.mainPicture)
                    .placeholder(R.drawable.background_img_placeholder)
                    .error(R.drawable.background_img_placeholder)
                    .centerCrop()
                    .into(ivPoster)
            }

            itemView.setOnClickListener{ onClickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayScheduleAnimeVH {
        return TodayScheduleAnimeVH(
            ViewListTodayScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodayScheduleAnimeVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}