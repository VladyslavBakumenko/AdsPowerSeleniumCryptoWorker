package org.example.data.network.adsPowerRetrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AdsPowerRetrofit {

    companion object {
        private const val ADS_POWER_API_BASE_URL = "http://local.adspower.net:50325"
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            if (instance == null) instance = getAdsPowerRetrofit()
            instance?.create(AdsPowerApiService::class.java)
            return instance!!
        }

        private fun getAdsPowerRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(ADS_POWER_API_BASE_URL)
                    .client(createMainOkHttp())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        private fun createLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            return logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        private fun createMainOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(createLoggingInterceptor())
                    .build()
        }
    }
}