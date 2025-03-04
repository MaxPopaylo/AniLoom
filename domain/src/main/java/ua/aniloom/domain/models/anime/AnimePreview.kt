package ua.aniloom.domain.models.anime

import ua.aniloom.domain.models.IBaseDiffModel

data class AnimePreview(
    override val id: Int,
    val title: String,
    val mainPicture: String,

    val mediaType: MediaType,
    val aired: Aired,
    val numEps: Int,

    val score: Score
): IBaseDiffModel<Int>
