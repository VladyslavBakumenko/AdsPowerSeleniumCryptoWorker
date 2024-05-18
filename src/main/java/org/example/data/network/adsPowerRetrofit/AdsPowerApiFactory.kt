package org.example.data.network.adsPowerRetrofit

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.data.network.responses.StartBrowserResponse

@OptIn(ExperimentalCoroutinesApi::class)
class AdsPowerApiFactory {

    private val adsPowerApiService = AdsPowerApiService.getInstance()

    private val openBrowserFlow: MutableStateFlow<StartBrowserResponse?> = MutableStateFlow(null)

    fun getOpenBrowserFlow() = openBrowserFlow

    suspend fun openBrowser(userId: String) {
        openBrowserFlow.value = adsPowerApiService.openBrowser(userId).body()
    }

    suspend fun testOpenBrowser(userId: String) = adsPowerApiService.openBrowser(userId).body()


    suspend fun stopBrowser(userId: String) {
        adsPowerApiService.stopBrowser(userId)
    }

    suspend fun testStopBrowser(userId: String) = adsPowerApiService.stopBrowser(userId)

    suspend fun checkBrowserStatus(userId: String) {
        adsPowerApiService.checkBrowserStatus(userId)
    }
}