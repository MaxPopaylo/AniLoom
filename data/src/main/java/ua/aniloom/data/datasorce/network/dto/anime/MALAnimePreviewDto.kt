package ua.aniloom.data.datasorce.network.dto.anime

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.aniloom.data.datasorce.network.dto.DtoMapper
import ua.aniloom.domain.models.anime.Aired
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.domain.models.anime.MediaType
import ua.aniloom.domain.models.anime.Score

@JsonClass(generateAdapter = true)
data class MALAnimePreviewDto(
    val id: Int,
    val title: String,
    @Json(name = "main_picture") val mainPicture: MainPictureDto,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "start_date") val startDate: String,
    @Json(name = "end_date") val endDate: String?,
    @Json(name = "start_season") val startSeason: StartSeasonDto,
    @Json(name = "num_episodes") val numEpisodes: Int,
    val mean: Double,
    @Json(name = "num_scoring_users") val numScoringUsers: Int
) : DtoMapper<MALAnimePreviewDto, AnimePreview> {
    override fun mapToDomain(): AnimePreview =
        AnimePreview(
            id = id,
            title = title,
            mainPicture = mainPicture.medium,
            mediaType = MediaType.valueOf(mediaType.uppercase()),
            aired = Aired(
                startData = startDate,
                endData = endDate,
                year = startSeason.year,
                season = startSeason.season
            ),
            numEps = numEpisodes,
            score = Score(
                score = mean,
                numScoringUsers = numScoringUsers
            )
        )

    companion object {
        const val MAL_FIELDS = "media_type,start_date,end_date,start_season,num_episodes,mean,num_scoring_users"
    }
}