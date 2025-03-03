package ua.aniloom.presentation.pages.search

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.aniloom.R
import ua.aniloom.databinding.FragmentSearchPageBinding
import ua.aniloom.domain.models.anime.Genres
import ua.aniloom.presentation.common.base.BaseFragment
import ua.aniloom.presentation.common.adapters.HorizontalAnimeAdapter


class SearchPageFragment : BaseFragment<SearchViewModel, FragmentSearchPageBinding>(
    R.layout.fragment_search_page
) {
    override val binding by viewBinding (FragmentSearchPageBinding::bind)
    override val viewModel by viewModel<SearchViewModel>()

    private val airingAnimeAdapter by lazy (LazyThreadSafetyMode.NONE) {
        HorizontalAnimeAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
        })
    }

    private val historyAdapter by lazy (LazyThreadSafetyMode.NONE) {
        HorizontalAnimeAdapter(onClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
        })
    }

    override fun initialize() {
        setupAiringAnimeRecycler()


        with(binding) {
            vSearchField.apply {
                setItems(
                    items = Genres.entries.toList(),
                    labelProvider = { it.genre.name },
                    onItemClick = {}
                )
                setOnSearchListener {  }
            }

            vCharts.apply {
                bMangaCharts.setOnClickListener {  }
                bAnimeCharts.setOnClickListener {  }
            }

            binding.rvHistoryList.apply {
                adapter = historyAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }

        arguments?.let {

        }

        viewLifecycleOwner.lifecycleScope.launch {
            historyAdapter.submitData(PagingData.from(emptyList()))
        }
    }

    override fun setupRequests() {
        fetchAiringAnime()
    }

    private fun setupAiringAnimeRecycler() = with(binding) {
        rvAiringAnimeList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvAiringAnimeList.adapter = airingAnimeAdapter

        airingAnimeAdapter.addLoadStateListener { loadState ->
            rvAiringAnimeList.isVisible = loadState.refresh is LoadState.NotLoading
        }
    }

    private fun fetchAiringAnime() {
        viewModel.fetchAiringRankingAnime().collectPaging {
            airingAnimeAdapter.submitData(it)
        }
    }
}
