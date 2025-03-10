package ua.aniloom.domain.models.anime

import ua.aniloom.domain.models.IBaseDiffModel
import ua.aniloom.domain.models.PreviewModel

data class AnimePreview(
    override val id: Int,
    override val title: String,
    override val mainPicture: String,
    override val mediaType: MediaType,
    override val aired: Aired,
    override val numEps: Int,
    override val score: Score
): IBaseDiffModel<Int>, PreviewModel
