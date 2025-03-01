package ua.aniloom.domain.models.anime

data class AnimeDetails(
    val id: Int,
    val title: String,
    val alternativeTitles: AlternativeTitles,
    val mainPicture: String,
    val synopsis: String,

    val mediaType: MediaType,
    val source: String,
    val status: Status,
    val rating: Rating,

    val aired: Aired,
    val broadcast: Broadcast,
    val numEps: Int,
    val averageEpsDuration: Int,

    val score: Score,
    val statistics: Statistics,
    val myListStatus: MyListStatus,

    val genres: List<Tag>,
    val themes: List<Tag>,
    val studios: List<Tag>
)
