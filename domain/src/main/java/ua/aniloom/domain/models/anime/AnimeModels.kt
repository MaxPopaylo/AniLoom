package ua.aniloom.domain.models.anime

import java.time.LocalDate

data class AlternativeTitles(
    val synonyms: List<String>,
    val en: String,
    val ja: String
)

data class Aired(
    val startData: LocalDate,
    val endData: LocalDate?,
    val year: Int,
    val season: String
)

data class Score(
    val score: Double,
    val numScoringUsers: Int
)

data class MyListStatus(
    val status: WatchingStatus,
    val score: Int,
    val numEpisodeWatched: Int,
    val isReWatching: Boolean
)

data class Statistics(
    val watching: Int,
    val completed: Int,
    val onHold: Int,
    val dropped: Int,
    val planToWatch: Int,
    val numListUsers: Int
)

data class Tag(
    val id: Int,
    val name: String
)

data class Broadcast(
    val day: String,
    val time: String
)
