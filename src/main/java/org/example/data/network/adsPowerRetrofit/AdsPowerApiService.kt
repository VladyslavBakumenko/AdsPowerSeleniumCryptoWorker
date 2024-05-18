package org.example.data.network.adsPowerRetrofit

import org.example.data.network.responses.StartBrowserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsPowerApiService {

    @GET("/api/v1/browser/start")
    suspend fun openBrowser(@Query("user_id") userId: String): Response<StartBrowserResponse>

    @GET("/api/v1/browser/stop")
    suspend fun stopBrowser(@Query("user_id") userId: String): Response<StartBrowserResponse>

    @GET("/api/v1/browser/active")
    suspend fun checkBrowserStatus(@Query("user_id") userId: String): Response<StartBrowserResponse>

    companion object {
        private var instance: AdsPowerApiService? = null
        fun getInstance(): AdsPowerApiService {
            if (instance == null) instance = AdsPowerRetrofit.getInstance().create(AdsPowerApiService::class.java)
            return instance!!
        }
    }
}