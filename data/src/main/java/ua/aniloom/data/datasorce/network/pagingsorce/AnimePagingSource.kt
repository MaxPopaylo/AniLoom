package ua.aniloom.data.datasorce.network.pagingsorce

import ua.aniloom.data.datasorce.network.api.AnimeApi
import ua.aniloom.data.datasorce.network.dto.anime.AnimePreviewDto
import ua.aniloom.domain.models.anime.AnimePreview

class AnimePagingSource(
    private val api: AnimeApi
): BaseMALPagingSource<AnimePreview, AnimePreviewDto>(
    request = { limit, offset ->
        api.getAiringRankingAnime(limit, offset)
    }
)