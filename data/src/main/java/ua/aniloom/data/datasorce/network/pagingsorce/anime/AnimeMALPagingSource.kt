package ua.aniloom.data.datasorce.network.pagingsorce.anime

import com.skydoves.sandwich.ApiResponse
import ua.aniloom.data.datasorce.network.dto.MALPagingDto
import ua.aniloom.data.datasorce.network.dto.anime.MALAnimePreviewDto
import ua.aniloom.data.datasorce.network.pagingsorce.BaseMALPagingSource
import ua.aniloom.domain.models.anime.AnimePreview

class AnimeMALPagingSource(
    request: suspend (limit: Int, offset: Int) -> ApiResponse<MALPagingDto<MALAnimePreviewDto>>
): BaseMALPagingSource<AnimePreview, MALAnimePreviewDto>(
    request = request
)