package ua.aniloom.data.datasorce.network.dto

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