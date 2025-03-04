package ua.aniloom.presentation.common.views

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ua.aniloom.databinding.ViewCarouselMediaBinding
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.presentation.common.adapters.StackedAnimeCardAdapter
import ua.aniloom.presentation.common.utils.SliderTransformer
import ua.aniloom.presentation.common.utils.extensions.formatToString

class CarouselMediaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewCarouselMediaBinding =
        ViewCarouselMediaBinding.inflate(LayoutInflater.from(context), this, true)

    private fun setupView(adapter: StackedAnimeCardAdapter) {
        with(binding) {
            posterPager.apply {
                setAdapter(adapter)

                offscreenPageLimit = 3
                setPageTransformer(SliderTransformer(3))

                setPadding(0, 0, getPagerPaddingEnd(context, 190f), 0)
                (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        updateTextFields(adapter.getItemByPosition(position))
                    }
                })
            }

            dotsIndicator.apply {
                attachToViewPager2(posterPager)
                setDotCount(1)
                setFadingDotCount(3)
            }
        }
    }

    private fun updateTextFields(item: AnimePreview?) {
        item?.let {
            with(binding) {
                tvTitle.text = it.title
                tvYear.text = it.aired.year.formatToString()
                tvRating.text = it.score.score.formatToString()
            }
        }
    }

    fun setupAdapter(adapter: StackedAnimeCardAdapter) {
        setupView(adapter)
    }

    fun setupContainerClickListener(onClickListener: () -> Unit) {
        binding.carouselContainer.setOnClickListener { onClickListener() }
    }

    private fun getPagerPaddingEnd(context: Context, pagerWidth: Float): Int {
        val screenWidth =  context.resources.configuration.screenWidthDp
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            screenWidth - (pagerWidth + 70f),
            context.resources.displayMetrics
        ).toInt()
    }


}