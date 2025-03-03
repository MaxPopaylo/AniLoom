package ua.aniloom.presentation.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewVerticalListItemBinding
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.presentation.common.extensions.formatToString

class HorizontalAnimeAdapter(
    private val onClickListener: (AnimePreview) -> Unit
) :
    PagingDataAdapter<AnimePreview, HorizontalAnimeAdapter.HorizontalAnimeVH>(AnimeDiffItemCallback)
{

    inner class HorizontalAnimeVH(private val binding: ViewVerticalListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: AnimePreview
        ) {
            with(binding) {
                tvName.text = item.title
                tvYear.text = item.aired.year.formatToString()
                tvRating.text = item.score.score.formatToString()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAnimeVH {
        return HorizontalAnimeVH(
            ViewVerticalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalAnimeVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}

private object AnimeDiffItemCallback : DiffUtil.ItemCallback<AnimePreview>() {
    override fun areItemsTheSame(oldItem: AnimePreview, newItem: AnimePreview): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: AnimePreview, newItem: AnimePreview): Boolean = oldItem == newItem
}