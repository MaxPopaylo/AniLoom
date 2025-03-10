package ua.aniloom.presentation.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.aniloom.databinding.ViewTodayScheduleBinding
import ua.aniloom.domain.models.IBaseDiffModel
import ua.aniloom.domain.models.PreviewModel
import ua.aniloom.presentation.common.adapters.TodaySchedulePreviewAdapter
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

    fun <T> setupView(adapter: TodaySchedulePreviewAdapter<T>)
    where T : PreviewModel, T : IBaseDiffModel<Int> {
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