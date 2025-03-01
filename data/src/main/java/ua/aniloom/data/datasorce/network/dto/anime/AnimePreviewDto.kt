package ua.aniloom.data.datasorce.network.dto.anime

import ua.aniloom.data.datasorce.network.dto.DtoMapper
import ua.aniloom.domain.models.anime.Aired
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.domain.models.anime.MediaType
import ua.aniloom.domain.models.anime.Score

data class AnimePreviewDto(
    val id: Int,
    val title: String,
    val mainPicture: MainPictureDto,
    val mediaType: String,
    val startDate: String,
    val endDate: String?,
    val startSeason: StartSeasonDto,
    val numEpisodes: Int,
    val mean: Double,
    val numScoringUsers: Int
) : DtoMapper<AnimePreviewDto, AnimePreview> {
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