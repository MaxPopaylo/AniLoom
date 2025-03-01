package ua.aniloom.data.datasorce.network.dto

data class PagingDto<DTO : DtoMapper<DTO, *>> (
    val paging: Paging,
    val data: List<PagingData<DTO>>
)

data class Paging(
    val previous: String?,
    val next: String?
)

data class PagingData<DTO : DtoMapper<DTO, *>>(
    val node: DTO
)