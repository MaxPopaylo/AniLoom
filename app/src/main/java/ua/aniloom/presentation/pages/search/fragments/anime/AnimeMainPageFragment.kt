package ua.aniloom.presentation.pages.search.fragments.anime

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.aniloom.R
import ua.aniloom.databinding.FragmentAnimeMainPageBinding
import ua.aniloom.presentation.common.adapters.HorizontalAnimeAdapter
import ua.aniloom.presentation.common.adapters.StackedAnimeCardAdapter
import ua.aniloom.presentation.common.adapters.TodayScheduleAnimeAdapter
import ua.aniloom.presentation.common.base.BaseFragment

class AnimeMainPageFragment : BaseFragment<AnimeMainViewModel, FragmentAnimeMainPageBinding>(
    R.layout.fragment_anime_main_page
) {

    override val binding by viewBinding (FragmentAnimeMainPageBinding::bind)
    override val viewModel by viewModel<AnimeMainViewModel>()

    private val airingAnimeAdapter by lazy (LazyThreadSafetyMode.NONE) {
        HorizontalAnimeAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
        })
    }

    private val rankingAnimeAdapter by lazy (LazyThreadSafetyMode.NONE) {
        StackedAnimeCardAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
        })
    }

    private val todayScheduleAnimeAdapter by lazy (LazyThreadSafetyMode.NONE) {
        TodayScheduleAnimeAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
        })
    }


    override fun initialize() {
        setupRankingAnimeCarousel()
        setupAiringAnimeRecycler()
        setupTodaySchedule()
    }

    override fun setupRequests() {
        fetchRankingAnime()
        fetchAiringAnime()
        fetchTodayScheduleAnime()
    }


    private fun setupTodaySchedule() = with(binding) {
        vTodaySchedule.setupAdapter(todayScheduleAnimeAdapter)
        todayScheduleAnimeAdapter.addLoadStateListener { loadState ->
            when(loadState.refresh){
                is LoadState.Loading -> vTodaySchedule.showShimmer()
                is LoadState.NotLoading -> vTodaySchedule.stopShimmer()
                is LoadState.Error -> {
                    vTodaySchedule.isVisible = false
                }
            }
        }
    }

    private fun setupAiringAnimeRecycler() = with(binding) {
        vAiringAnimeList.setupView(
            airingAnimeAdapter,
            title = requireContext().getString(R.string.airing),
            buttonOnClick = {}
        )

        airingAnimeAdapter.addLoadStateListener { loadState ->
            when(loadState.refresh){
                is LoadState.Loading -> vAiringAnimeList.showShimmer()
                is LoadState.NotLoading -> vAiringAnimeList.stopShimmer()
                is LoadState.Error -> {
                    vAiringAnimeList.isVisible = false
                }
            }
        }
    }

    private fun setupRankingAnimeCarousel() = with(binding) {
        vAnimeCarousel.setupAdapter(rankingAnimeAdapter)
        rankingAnimeAdapter.addLoadStateListener { loadState ->
            when(loadState.refresh){
                is LoadState.Loading -> vAnimeCarousel.showShimmer()
                is LoadState.NotLoading -> vAnimeCarousel.stopShimmer()
                is LoadState.Error -> {
                    vAnimeCarousel.isVisible = false
                }
            }
        }
    }


    private fun fetchTodayScheduleAnime() {
        viewModel.todayScheduleAnimeFlow.collectPaging {
            todayScheduleAnimeAdapter.submitData(it)
        }
    }

    private fun fetchAiringAnime() {
        viewModel.airingRankingAnimeFlow.collectPaging {
            airingAnimeAdapter.submitData(it)
        }
    }

    private fun fetchRankingAnime() {
        viewModel.rankingAnimeFlow.collectPaging {
            rankingAnimeAdapter.submitData(it)
        }
    }
}
