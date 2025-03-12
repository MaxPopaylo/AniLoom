package ua.aniloom.domain.models.manga

import ua.aniloom.domain.models.IBaseDiffModel
import ua.aniloom.domain.models.PreviewModel
import ua.aniloom.domain.models.anime.Aired
import ua.aniloom.domain.models.anime.MediaType
import ua.aniloom.domain.models.anime.Score

data class MangaPreview(
    override val id: Int,
    override val title: String,
    override val mainPicture: String,
    override val mediaType: MediaType,
    override val aired: Aired,
    override val numEps: Int,
    override val score: Score
): IBaseDiffModel<Int>, PreviewModel
