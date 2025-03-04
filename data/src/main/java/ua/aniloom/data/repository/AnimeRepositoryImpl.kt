package ua.aniloom.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.aniloom.data.datasorce.network.api.AnimeApi
import ua.aniloom.data.datasorce.network.pagingsorce.AnimePagingSource
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.domain.repository.AnimeRepository

class AnimeRepositoryImpl (
    private val api: AnimeApi
): BaseRepository(), AnimeRepository {

    override fun fetchAiringRankingAnime() = doPagingRequest(AnimePagingSource(
        request = { limit, offset ->
            api.getAiringRankingAnime(limit, offset)
        }
    ))
    override fun fetchRankingAnime(): Flow<PagingData<AnimePreview>> = doPagingRequest(AnimePagingSource(
        request = { limit, offset ->
            api.getRankingAnime(limit, offset)
        }
    ))

}
