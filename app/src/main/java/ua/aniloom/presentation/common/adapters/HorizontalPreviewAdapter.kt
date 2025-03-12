package ua.aniloom.presentation.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.aniloom.R
import ua.aniloom.databinding.ViewListVerticalItemBinding
import ua.aniloom.domain.models.IBaseDiffModel
import ua.aniloom.domain.models.PreviewModel
import ua.aniloom.presentation.common.base.BaseDiffUtilItemCallback
import ua.aniloom.presentation.common.utils.extensions.formatToString

class HorizontalPreviewAdapter<T>(
    private val onClickListener: (T) -> Unit
) : PagingDataAdapter<T, HorizontalPreviewAdapter<T>.HorizontalPreviewVH>(BaseDiffUtilItemCallback())
        where T : PreviewModel, T : IBaseDiffModel<Int>
{

    inner class HorizontalPreviewVH(private val binding: ViewListVerticalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: T
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalPreviewVH {
        return HorizontalPreviewVH(
            ViewListVerticalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalPreviewVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}
