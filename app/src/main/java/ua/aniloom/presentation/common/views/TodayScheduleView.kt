package ua.aniloom.presentation.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.aniloom.databinding.ViewTodayScheduleBinding
import ua.aniloom.presentation.common.adapters.TodayScheduleAnimeAdapter
import ua.aniloom.presentation.common.utils.extensions.parseToFormat
import ua.aniloom.presentation.common.utils.extensions.setupRecycler

import java.util.Date

class TodayScheduleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ViewTodayScheduleBinding =
        ViewTodayScheduleBinding.inflate(LayoutInflater.from(context), this, true)

    private fun setupView(adapter: TodayScheduleAnimeAdapter) {
        with(binding) {
            recyclerview.apply {
                setupRecycler(
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false),
                    adapter
                )
            }

            tvDate.text = Date().parseToFormat("d,  EEEE")
        }
    }

    fun setupAdapter(adapter: TodayScheduleAnimeAdapter) {
        setupView(adapter)
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