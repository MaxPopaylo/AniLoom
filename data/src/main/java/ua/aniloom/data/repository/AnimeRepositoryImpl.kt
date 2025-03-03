package ua.aniloom.data.repository

import ua.aniloom.data.datasorce.network.api.AnimeApi
import ua.aniloom.data.datasorce.network.pagingsorce.AnimePagingSource
import ua.aniloom.domain.repository.AnimeRepository

class AnimeRepositoryImpl (
    private val api: AnimeApi
): BaseRepository(), AnimeRepository {

    override fun fetchAiringRankingAnime() = doPagingRequest(AnimePagingSource(api))

}