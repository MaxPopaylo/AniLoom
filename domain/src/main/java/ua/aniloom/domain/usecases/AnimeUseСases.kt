package ua.aniloom.domain.usecases

import ua.aniloom.domain.repository.AnimeRepository

class FetchAiringRankingAnimeUseCase(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke() = animeRepository.fetchAiringRankingAnime()
}

class FetchRankingAnimeUseCase(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke() = animeRepository.fetchRankingAnime()
}

class FetchScheduleTodayAnimeUseCase(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(day: String) = animeRepository.fetchScheduleTodayAnime(day)
}