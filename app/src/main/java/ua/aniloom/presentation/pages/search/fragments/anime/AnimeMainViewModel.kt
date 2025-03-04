package ua.aniloom.presentation.pages.search.fragments.anime

import androidx.lifecycle.viewModelScope
import ua.aniloom.domain.usecases.FetchAiringRankingAnimeUseCase
import ua.aniloom.domain.usecases.FetchRankingAnimeUseCase
import ua.aniloom.presentation.common.base.BaseViewModel
import ua.aniloom.presentation.common.utils.extensions.mapPaging

class AnimeMainViewModel(
    private val fetchAiringRankingAnime: FetchAiringRankingAnimeUseCase,
    private val fetchRankingAnime: FetchRankingAnimeUseCase
): BaseViewModel() {
    fun fetchAiringRankingAnime() = fetchAiringRankingAnime.invoke().mapPaging(viewModelScope) { it }
    fun fetchRankingAnime() = fetchRankingAnime.invoke().mapPaging(viewModelScope) { it }
}