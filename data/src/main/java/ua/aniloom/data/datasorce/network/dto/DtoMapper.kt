package ua.aniloom.data.datasorce.network.dto

interface DtoMapper<DTO, Domain> {
    fun mapToDomain(): Domain
}
