package ua.aniloom.presentation.pages.search

import androidx.lifecycle.viewModelScope
import ua.aniloom.domain.usecases.FetchAiringRankingAnimeUseCase
import ua.aniloom.presentation.common.base.BaseViewModel
import ua.aniloom.presentation.common.extensions.mapPaging

class SearchViewModel(
    private val fetchAiringRankingAnime: FetchAiringRankingAnimeUseCase
): BaseViewModel() {

    fun fetchAiringRankingAnime() = fetchAiringRankingAnime.invoke().mapPaging(viewModelScope) { it }
}