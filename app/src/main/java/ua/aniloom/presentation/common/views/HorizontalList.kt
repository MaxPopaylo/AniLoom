package ua.aniloom.presentation.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.aniloom.databinding.ViewHorizontalListBinding
import ua.aniloom.presentation.common.adapters.HorizontalAnimeAdapter
import ua.aniloom.presentation.common.utils.extensions.setupRecycler

class HorizontalList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewHorizontalListBinding =
        ViewHorizontalListBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupView(
        adapter: HorizontalAnimeAdapter,
        title: String,
        buttonOnClick: () -> Unit
    ) = with(binding) {
        button.text = title
        button.setOnClickListener { buttonOnClick() }

        recyclerview.setupRecycler(
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
            adapter
        )
    }

    fun showShimmer() = with(binding) {
        recyclerview.isVisible = false
        shimmer.startShimmer()
        shimmer.isVisible = true
    }

    fun stopShimmer() = with(binding) {
        shimmer.stopShimmer()
        shimmer.isVisible = false
        recyclerview.isVisible = true
    }
}