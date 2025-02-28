package ua.aniloom.domain.models.anime

data class Images(
    val jpg: ImageUrls,
    val webp: ImageUrls
)

data class ImageUrls(
    val imageUrl: String,
    val smallImageUrl: String,
    val largeImageUrl: String
)