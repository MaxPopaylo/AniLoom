package ua.aniloom.domain.models.anime

data class AnimePreview(
    val id: Int,
    val title: String,
    val mainPicture: String,

    val mediaType: MediaType,
    val aired: Aired,
    val numEps: Int,

    val score: Score
)
