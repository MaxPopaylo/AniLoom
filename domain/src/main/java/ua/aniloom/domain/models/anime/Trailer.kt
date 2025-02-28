package ua.aniloom.domain.models.anime

data class Trailer(
    val youtubeId: String?,
    val url: String?,
    val embedUrl: String?,
    val images: TrailerImages
)

data class TrailerImages(
    val imageUrl: String?,
    val smallImageUrl: String?,
    val mediumImageUrl: String?,
    val largeImageUrl: String?,
    val maximumImageUrl: String?
)
