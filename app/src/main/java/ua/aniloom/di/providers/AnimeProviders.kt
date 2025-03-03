package ua.aniloom.di.providers

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ua.aniloom.data.datasorce.network.api.AnimeApi
import ua.aniloom.di.BASE_API_URL

fun provideAnimeApi(
    httpClient: OkHttpClient,
    moshi: Moshi
): AnimeApi =
    Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .client(httpClient)
        .build()
        .create(AnimeApi::class.java)