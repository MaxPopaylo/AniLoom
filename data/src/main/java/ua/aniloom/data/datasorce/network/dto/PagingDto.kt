package ua.aniloom.data.datasorce.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MALPagingDto<DTO : DtoMapper<DTO, *>> (
    val paging: MALPaging,
    val data: List<MALPagingData<DTO>>
)

data class MALPaging(
    val previous: String?,
    val next: String?
)

data class MALPagingData<DTO : DtoMapper<DTO, *>>(
    val node: DTO
)


@JsonClass(generateAdapter = true)
data class JikanPagingDto<DTO : DtoMapper<DTO, *>> (
    @Json(name = "pagination") val paging: JikanPaging,
    val data: List<DTO>
)

@JsonClass(generateAdapter = true)
data class JikanPaging(
    @Json(name = "last_visible_page") val lastVisiblePage: Int,
    @Json(name = "has_next_page") val hasNextPage: Boolean,
    @Json(name = "current_page") val currentPage: Int
)
