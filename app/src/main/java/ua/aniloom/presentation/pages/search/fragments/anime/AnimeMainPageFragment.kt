package ua.aniloom.presentation.pages.search.fragments.anime

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.aniloom.R
import ua.aniloom.databinding.FragmentAnimeMainPageBinding
import ua.aniloom.presentation.common.adapters.HorizontalAnimeAdapter
import ua.aniloom.presentation.common.adapters.StackedAnimeCardAdapter
import ua.aniloom.presentation.common.adapters.TodayScheduleAnimeAdapter
import ua.aniloom.presentation.common.base.BaseFragment
import ua.aniloom.presentation.common.utils.extensions.setupRecycler

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
        setupAiringAnimeRecycler()
        setupRankingAnimeCarousel()
        setupTodaySchedule()
    }

    override fun setupRequests() {
        fetchAiringAnime()
        fetchRankingAnime()
        fetchTodayScheduleAnime()
    }


    private fun setupTodaySchedule() = with(binding) {
        vTodaySchedule.setupAdapter(todayScheduleAnimeAdapter)
        todayScheduleAnimeAdapter.addLoadStateListener { loadState ->
            vTodaySchedule.isVisible = loadState.refresh is LoadState.NotLoading
        }
    }

    private fun setupRankingAnimeCarousel() = with(binding) {
        vAnimeCarousel.setupAdapter(rankingAnimeAdapter)
        rankingAnimeAdapter.addLoadStateListener { loadState ->
            vAnimeCarousel.isVisible = loadState.refresh is LoadState.NotLoading
        }
    }

    private fun setupAiringAnimeRecycler() = with(binding) {
        rvAiringAnimeList.setupRecycler(
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
            airingAnimeAdapter
        )

        airingAnimeAdapter.addLoadStateListener { loadState ->
            rvAiringAnimeList.isVisible = loadState.refresh is LoadState.NotLoading
        }
    }

    private fun fetchTodayScheduleAnime() {
        viewModel.fetchAiringRankingAnime().collectPaging {
            todayScheduleAnimeAdapter.submitData(it)
        }
    }

    private fun fetchAiringAnime() {
        viewModel.fetchAiringRankingAnime().collectPaging {
            airingAnimeAdapter.submitData(it)
        }
    }

    private fun fetchRankingAnime() {
        viewModel.fetchRankingAnime().collectPaging {
            rankingAnimeAdapter.submitData(it)
        }
    }
}
