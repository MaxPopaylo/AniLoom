package ua.aniloom.di.providers

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ua.aniloom.data.datasorce.network.interceptors.ClientMALInterceptor
import ua.aniloom.di.MAL_CLIENT_ID
import java.util.concurrent.TimeUnit

fun provideNotAuthenticatedClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val clientMALInterceptor = ClientMALInterceptor(
        clientId = MAL_CLIENT_ID
    )
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(clientMALInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideMoshi(): Moshi =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()