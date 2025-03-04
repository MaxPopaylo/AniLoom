package ua.aniloom.data.datasorce.network.pagingsorce

import com.skydoves.sandwich.ApiResponse
import ua.aniloom.data.datasorce.network.dto.PagingDto
import ua.aniloom.data.datasorce.network.dto.anime.AnimePreviewDto
import ua.aniloom.domain.models.anime.AnimePreview

class AnimePagingSource(
    request: suspend (limit: Int, offset: Int) -> ApiResponse<PagingDto<AnimePreviewDto>>
): BaseMALPagingSource<AnimePreview, AnimePreviewDto>(
    request = request
)