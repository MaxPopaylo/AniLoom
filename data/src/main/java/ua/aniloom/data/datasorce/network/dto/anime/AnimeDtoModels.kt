package ua.aniloom.data.datasorce.network.dto.anime

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MainPictureDto(
    val medium: String,
    val large: String
)

data class StartSeasonDto(
    val year: Int,
    val season: String
)


data class JikanImagesDto(
    val jpg: JikanJpgDto
)

@JsonClass(generateAdapter = true)
data class JikanJpgDto(
    @Json(name = "image_url") val imageUrl: String
)

data class JikanAiredDto(
    val from: String,
    val to: String?
)