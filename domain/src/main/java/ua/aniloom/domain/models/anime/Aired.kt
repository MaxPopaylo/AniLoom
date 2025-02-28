package ua.aniloom.domain.models.anime

data class Aired(
    val from: String,
    val to: String,
    val prop: AiredProp,
    val string: String
)

data class AiredProp(
    val from: DateProp,
    val to: DateProp
)

data class DateProp(
    val day: Int,
    val month: Int,
    val year: Int
)