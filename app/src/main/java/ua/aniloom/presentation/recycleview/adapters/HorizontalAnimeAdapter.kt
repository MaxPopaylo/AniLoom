package ua.aniloom.presentation.recycleview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewVerticalListItemBinding
import ua.aniloom.domain.models.anime.AnimeData

class HorizontalAnimeAdapter(
    private val onClickListener: (AnimeData) -> Unit
) :
    PagingDataAdapter<AnimeData, HorizontalAnimeAdapter.HorizontalAnimeVH>(AnimeDiffItemCallback)
{

    inner class HorizontalAnimeVH(private val binding: ViewVerticalListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: AnimeData
        ) {
            with(binding) {
                tvName.text = item.titleEnglish
                tvYear.text = "${item.year}"
                tvRating.text = "${item.score}"
                Glide.with(itemView.context)
                    .load(item.images.webp.imageUrl)
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


private object AnimeDiffItemCallback : DiffUtil.ItemCallback<AnimeData>() {
    override fun areItemsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean = oldItem.malId == newItem.malId
    override fun areContentsTheSame(oldItem: AnimeData, newItem: AnimeData): Boolean = oldItem == newItem
}