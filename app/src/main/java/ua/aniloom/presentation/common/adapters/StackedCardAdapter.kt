package ua.aniloom.presentation.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewListStackedCardItemBinding
import ua.aniloom.domain.models.IBaseDiffModel
import ua.aniloom.domain.models.PreviewModel
import ua.aniloom.presentation.common.base.BaseDiffUtilItemCallback

class StackedPreviewCardAdapter<T>(
    private val onClickListener: (T) -> Unit
): PagingDataAdapter<T, StackedPreviewCardAdapter<T>.StackedPreviewCardVH>(BaseDiffUtilItemCallback())
        where T : PreviewModel, T : IBaseDiffModel<Int>
{

    inner class StackedPreviewCardVH(private val binding: ViewListStackedCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: T
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackedPreviewCardVH {
        return StackedPreviewCardVH(
            ViewListStackedCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StackedPreviewCardVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun getItemByPosition(position: Int) = getItem(position)

}