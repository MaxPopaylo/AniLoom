package ua.aniloom.presentation.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewListVerticalItemBinding
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.presentation.common.base.BaseDiffUtilItemCallback
import ua.aniloom.presentation.common.utils.extensions.formatToString

class HorizontalAnimeAdapter(
    private val onClickListener: (AnimePreview) -> Unit
) :
    PagingDataAdapter<AnimePreview, HorizontalAnimeAdapter.HorizontalAnimeVH>(BaseDiffUtilItemCallback())
{

    inner class HorizontalAnimeVH(private val binding: ViewListVerticalItemBinding) : RecyclerView.ViewHolder(binding.root) {
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
            ViewListVerticalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalAnimeVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}
