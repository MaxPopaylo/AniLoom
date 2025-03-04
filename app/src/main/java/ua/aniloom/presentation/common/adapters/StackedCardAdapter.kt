package ua.aniloom.presentation.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewStackedCardItemBinding
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.presentation.common.base.BaseDiffUtilItemCallback

class StackedAnimeCardAdapter(
    private val onClickListener: (AnimePreview) -> Unit
): PagingDataAdapter<AnimePreview, StackedAnimeCardAdapter.StackedAnimeCardVH>(
    BaseDiffUtilItemCallback()
) {

    inner class StackedAnimeCardVH(private val binding: ViewStackedCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: AnimePreview
        ) {
            Glide.with(itemView.context)
                .load(item.mainPicture)
                .placeholder(R.drawable.background_img_placeholder)
                .error(R.drawable.background_img_placeholder)
                .centerCrop()
                .into(binding.ivPoster)

            itemView.setOnClickListener { onClickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackedAnimeCardVH {
        return StackedAnimeCardVH(
            ViewStackedCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StackedAnimeCardVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun getItemByPosition(position: Int) = getItem(position)

}