package ua.aniloom.data.datasorce.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ClientMALInterceptor(
    private val clientId: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-MAL-CLIENT-ID", clientId)
            .build()
        return chain.proceed(request)
    }
}