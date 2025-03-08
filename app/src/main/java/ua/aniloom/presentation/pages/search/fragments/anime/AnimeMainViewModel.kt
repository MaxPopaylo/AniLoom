package ua.aniloom.presentation.pages.search.fragments.anime

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.domain.usecases.FetchAiringRankingAnimeUseCase
import ua.aniloom.domain.usecases.FetchRankingAnimeUseCase
import ua.aniloom.domain.usecases.FetchScheduleTodayAnimeUseCase
import ua.aniloom.presentation.common.base.BaseViewModel
import ua.aniloom.presentation.common.utils.extensions.parseToFormat
import java.util.Date

class AnimeMainViewModel(
    private val fetchAiringRankingAnime: FetchAiringRankingAnimeUseCase,
    private val fetchRankingAnime: FetchRankingAnimeUseCase,
    private val fetchScheduleTodayAnime: FetchScheduleTodayAnimeUseCase
): BaseViewModel() {

    val airingRankingAnimeFlow: Flow<PagingData<AnimePreview>> by lazy {
        fetchAiringRankingAnime()
            .cachedIn(viewModelScope)
    }

    val rankingAnimeFlow: Flow<PagingData<AnimePreview>> by lazy {
        fetchRankingAnime()
            .cachedIn(viewModelScope)
    }

    val todayScheduleAnimeFlow: Flow<PagingData<AnimePreview>> by lazy {
        val currentDay = Date().parseToFormat("EEEE").lowercase()
        fetchScheduleTodayAnime(currentDay)
            .cachedIn(viewModelScope)
    }
}