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


    override fun initialize() {
        setupAiringAnimeRecycler()


        with(binding) {
            vCharts.apply {
                bMangaCharts.setOnClickListener {  }
                bAnimeCharts.setOnClickListener {  }
            }
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