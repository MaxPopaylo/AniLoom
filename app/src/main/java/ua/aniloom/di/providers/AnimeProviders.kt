package ua.aniloom.di.providers

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ua.aniloom.data.datasorce.network.api.JikanAnimeApi
import ua.aniloom.data.datasorce.network.api.MALAnimeApi
import ua.aniloom.di.BASE_JIKAN_API_URL
import ua.aniloom.di.BASE_MAL_API_URL

fun provideMALAnimeApi(
    httpClient: OkHttpClient,
    moshi: Moshi
): MALAnimeApi =
    Retrofit.Builder()
        .baseUrl(BASE_MAL_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .client(httpClient)
        .build()
        .create(MALAnimeApi::class.java)

fun provideJikanAnimeApi(
    httpClient: OkHttpClient,
    moshi: Moshi
): JikanAnimeApi =
    Retrofit.Builder()
        .baseUrl(BASE_JIKAN_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .client(httpClient)
        .build()
        .create(JikanAnimeApi::class.java)