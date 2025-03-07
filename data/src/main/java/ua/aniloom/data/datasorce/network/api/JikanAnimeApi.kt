package ua.aniloom.data.datasorce.network.api

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ua.aniloom.data.datasorce.network.dto.JikanPagingDto
import ua.aniloom.data.datasorce.network.dto.anime.JikanAnimePreviewDto

interface JikanAnimeApi {

    @GET("schedules?kids=false&unapproved=false&sfw=false")
    suspend fun getTodayScheduleAnime(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("day") day: String
    ): ApiResponse<JikanPagingDto<JikanAnimePreviewDto>>
}
