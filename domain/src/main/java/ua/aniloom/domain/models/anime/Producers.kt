package ua.aniloom.domain.models.anime

data class Producer(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Licensor(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Studio(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)