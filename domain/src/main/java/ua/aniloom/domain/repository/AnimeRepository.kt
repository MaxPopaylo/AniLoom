package ua.aniloom.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.aniloom.domain.models.anime.AnimePreview

interface AnimeRepository {
    fun fetchAiringRankingAnime(): Flow<PagingData<AnimePreview>>
    fun fetchRankingAnime(): Flow<PagingData<AnimePreview>>
    fun fetchScheduleTodayAnime(day: String): Flow<PagingData<AnimePreview>>
}