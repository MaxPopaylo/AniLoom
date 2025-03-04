package ua.aniloom.data.datasorce.network.api

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ua.aniloom.data.datasorce.network.dto.PagingDto
import ua.aniloom.data.datasorce.network.dto.anime.AnimePreviewDto

interface AnimeApi {
    @GET("anime/ranking?ranking_type=airing&fields=${AnimePreviewDto.MAL_FIELDS}")
    suspend fun getAiringRankingAnime(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse<PagingDto<AnimePreviewDto>>

    @GET("anime/ranking?ranking_type=all&fields=${AnimePreviewDto.MAL_FIELDS}")
    suspend fun getRankingAnime(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse<PagingDto<AnimePreviewDto>>
}
