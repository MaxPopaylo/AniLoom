package ua.aniloom.data.datasorce.network.dto.anime

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ua.aniloom.data.datasorce.network.dto.DtoMapper
import ua.aniloom.data.datasorce.network.dto.LocalDateFormatter
import ua.aniloom.domain.models.anime.Aired
import ua.aniloom.domain.models.anime.AnimePreview
import ua.aniloom.domain.models.anime.MediaType
import ua.aniloom.domain.models.anime.Score


@JsonClass(generateAdapter = true)
data class JikanAnimePreviewDto(
    @Json(name = "mal_id") val malId: Int,
    val title: String,
    val images: JikanImagesDto,
    val type: String,
    val aired: JikanAiredDto,
    val year: Int?,
    val season: String?,
    val episodes: Int?,
    val score: Double?,
    @Json(name = "scored_by") val scoredBy: Int?
) : DtoMapper<JikanAnimePreviewDto, AnimePreview> {
    override fun mapToDomain() =
        AnimePreview(
            id = malId,
            title = title,
            mainPicture = images.jpg.imageUrl,
            mediaType = MediaType.valueOf(type),
            aired = Aired(
                startData = LocalDateFormatter.jikanParse(aired.from),
                endData = aired.to?.let { LocalDateFormatter.jikanParse(it) },
                year = year ?: 0,
                season = season ?: "unknown"
            ),
            numEps = episodes ?: 0,
            score = Score(
                score = score ?: 0.0,
                numScoringUsers = scoredBy ?: 0
            )
        )
}