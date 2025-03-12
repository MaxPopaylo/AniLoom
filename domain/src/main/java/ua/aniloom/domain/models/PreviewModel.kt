package ua.aniloom.domain.models

import ua.aniloom.domain.models.anime.Aired
import ua.aniloom.domain.models.anime.MediaType
import ua.aniloom.domain.models.anime.Score

interface PreviewModel {
    val title: String
    val mainPicture: String
    val mediaType: MediaType
    val aired: Aired
    val numEps: Int
    val score: Score
}