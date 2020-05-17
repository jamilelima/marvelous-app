package dev.jamile.marvelous.network

import dev.jamile.marvelous.utils.Utils
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val PRIVATE_KEY = "0c5ae0dc3f977101488ad6fb8b40e6023a13667c"
private const val PUBLIC_KEY = "6a8e8f2bff7f320cfc8e12042d3760ee"

class RequestInterceptor {
    companion object {
        fun setupOkHttp(): OkHttpClient.Builder {
            return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalUrl = original.url
                    val timeStamp = Utils.getTimeStamp()
                    val url = originalUrl.newBuilder()
                        .addQueryParameter("apikey", PUBLIC_KEY)
                        .addQueryParameter("ts", timeStamp.toString())
                        .addQueryParameter("hash", Utils.md5("$timeStamp$PRIVATE_KEY$PUBLIC_KEY"))
                        .build()
                    return@addInterceptor chain.proceed(original.newBuilder().url(url).build())
                }
        }
    }
}