package ua.aniloom.presentation.pages.search

import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.aniloom.R
import ua.aniloom.databinding.FragmentSearchPageBinding
import ua.aniloom.domain.models.anime.Genres
import ua.aniloom.presentation.common.adapters.FragmentPagerAdapter
import ua.aniloom.presentation.common.base.BaseFragment
import ua.aniloom.presentation.pages.search.fragments.anime.AnimeMainPageFragment
import ua.aniloom.presentation.pages.search.fragments.manga.MangaMainPageFragment


class SearchPageFragment : BaseFragment<SearchViewModel, FragmentSearchPageBinding>(
    R.layout.fragment_search_page
) {
    override val binding by viewBinding (FragmentSearchPageBinding::bind)
    override val viewModel by viewModel<SearchViewModel>()

    private val pagerAdapter by lazy (LazyThreadSafetyMode.NONE) {
        FragmentPagerAdapter(childFragmentManager, lifecycle)
    }

    override fun initialize() {
        setupScreensPager()

        with(binding) {
            vSearchField.apply {
                setItems(
                    items = Genres.entries.toList(),
                    labelProvider = { it.genre.name },
                    onItemClick = {}
                )
                setOnSearchListener {  }
            }
        }
    }

    private fun setupScreensPager() = with(binding) {
        pagerAdapter.apply {
            addFragment(AnimeMainPageFragment(), getString(R.string.anime))
            addFragment(MangaMainPageFragment(), getString(R.string.manga))
        }

        searchScreensPager.apply {
            isSaveEnabled = true
            adapter = pagerAdapter
            isUserInputEnabled = false
        }

        TabLayoutMediator(searchScreensTab, searchScreensPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
            searchScreensPager.setCurrentItem(tab.position, true)
        }.attach()
    }


}
