package ua.aniloom.data.datasorce.network.api

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ua.aniloom.data.datasorce.network.dto.PagingDto
import ua.aniloom.data.datasorce.network.dto.anime.AnimePreviewDto

interface AnimeApi {
    @GET("anime/ranking")
    suspend fun getAiringRankingAnime(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String = AnimePreviewDto.MAL_FIELDS
    ): ApiResponse<PagingDto<AnimePreviewDto>>
}