package ua.aniloom.data.datasorce.network.api

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ua.aniloom.data.datasorce.network.dto.MALPagingDto
import ua.aniloom.data.datasorce.network.dto.anime.MALAnimePreviewDto

interface AnimeApi {
    @GET("anime/ranking?ranking_type=airing&fields=${MALAnimePreviewDto.MAL_FIELDS}")
    suspend fun getAiringRankingAnime(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse<MALPagingDto<MALAnimePreviewDto>>

    @GET("anime/ranking?ranking_type=all&fields=${MALAnimePreviewDto.MAL_FIELDS}")
    suspend fun getRankingAnime(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiResponse<MALPagingDto<MALAnimePreviewDto>>
}
