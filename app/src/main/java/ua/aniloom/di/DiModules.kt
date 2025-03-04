package ua.aniloom.di

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ua.aniloom.data.datasorce.network.api.AnimeApi
import ua.aniloom.data.repository.AnimeRepositoryImpl
import ua.aniloom.di.providers.provideAnimeApi
import ua.aniloom.di.providers.provideMoshi
import ua.aniloom.di.providers.provideNotAuthenticatedClient
import ua.aniloom.domain.repository.AnimeRepository
import ua.aniloom.domain.usecases.FetchAiringRankingAnimeUseCase
import ua.aniloom.domain.usecases.FetchRankingAnimeUseCase
import ua.aniloom.presentation.pages.search.SearchViewModel
import ua.aniloom.presentation.pages.search.fragments.anime.AnimeMainViewModel

class DiModules {
    private val networkModule = module {
        single<Moshi> { provideMoshi() }
        single<OkHttpClient>(named(NOT_AUTHENTICATED_CLIENT)) { provideNotAuthenticatedClient() }
    }

    private val animeModule = module {
        single<AnimeApi> { provideAnimeApi( get<OkHttpClient>(named(NOT_AUTHENTICATED_CLIENT)), get<Moshi>()) }
        singleOf(::AnimeRepositoryImpl) { bind<AnimeRepository>() }
        factoryOf(::FetchAiringRankingAnimeUseCase) { bind<FetchAiringRankingAnimeUseCase>() }
        factoryOf(::FetchRankingAnimeUseCase) { bind<FetchRankingAnimeUseCase>() }
    }

    private val viewModelsModule = module {
        viewModelOf(::SearchViewModel)
        viewModelOf(::AnimeMainViewModel)
    }

    val appModules = listOf(networkModule, animeModule, viewModelsModule)
}