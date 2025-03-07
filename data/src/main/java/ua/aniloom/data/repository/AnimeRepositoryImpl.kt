package ua.aniloom.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.aniloom.data.datasorce.network.api.JikanAnimeApi
import ua.aniloom.data.datasorce.network.api.MALAnimeApi
import ua.aniloom.data.datasorce.network.pagingsorce.anime.AnimeMALPagingSource
import ua.aniloom.data.datasorce.network.pagingsorce.anime.AnimeScheduleTodayPagingSource
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.domain.repository.AnimeRepository

class AnimeRepositoryImpl (
    private val malApi: MALAnimeApi,
    private val jApi: JikanAnimeApi
): BaseRepository(), AnimeRepository {

    override fun fetchAiringRankingAnime() = doPagingRequest(AnimeMALPagingSource(
        request = { limit, offset ->
            malApi.getAiringRankingAnime(limit, offset)
        }
    ))

    override fun fetchRankingAnime(): Flow<PagingData<AnimePreview>> = doPagingRequest(
        AnimeMALPagingSource(
        request = { limit, offset ->
            malApi.getRankingAnime(limit, offset)
        }
    ))

    override fun fetchScheduleTodayAnime(day: String): Flow<PagingData<AnimePreview>> {
        return Pager(
            config = PagingConfig(
                pageSize = 4,
                prefetchDistance = 4,
                enablePlaceholders = true,
                initialLoadSize = 12,
                maxSize = Int.MAX_VALUE,
                jumpThreshold = Int.MIN_VALUE
            ),
            pagingSourceFactory = {
                AnimeScheduleTodayPagingSource(
                    day = day,
                    request = { limit, page, day ->
                        jApi.getTodayScheduleAnime(limit, page, day)
                    }
                )
            }
        ).flow
    }
}