package org.example.presentation

import kotlinx.coroutines.*
import org.example.data.network.adsPowerRetrofit.AdsPowerApiFactory
import org.example.data.network.responses.StartBrowserResponse
import org.example.data.utils.CHROME_DEBUGGER_ADDRESS_KEY
import org.example.data.utils.CHROME_DRIVER_KEY
import org.example.data.utils.dataClasses.AdsPowerProfileScriptData
import org.example.data.utils.enums.ScriptType
import org.example.domain.ScriptsQueueManager
import org.example.presentation.scripts.OverlayScript
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

val coroutineScope = CoroutineScope(Dispatchers.IO)
val adsPowerApiFactory = AdsPowerApiFactory()
val scriptsQueue = ScriptsQueueManager().generateScriptsQueue()
var currentProcessedAdsPowerScriptData: AdsPowerProfileScriptData? = null

suspend fun main() {
    coroutineScope.launch {
        startLaunchAdsProfileLoop()
    }.join()
}

private suspend fun startLaunchAdsProfileLoop() {
    for (i in scriptsQueue) {
        currentProcessedAdsPowerScriptData = i
        launchAdsPowerProfileAndScripts(i.adsPowerProfileData.profileId)
        adsPowerApiFactory.testStopBrowser(i.adsPowerProfileData.profileId)
    }
}

private suspend fun launchAdsPowerProfileAndScripts(profileId: String) {
    val openBrowserRequestResponse = adsPowerApiFactory.testOpenBrowser(profileId)
    initChromeDriver(openBrowserRequestResponse!!)
}

private fun initChromeDriver(startBrowserResponse: StartBrowserResponse) {
    System.setProperty(CHROME_DRIVER_KEY, startBrowserResponse.data.webdriver)
    val chromeOptions = ChromeOptions().setExperimentalOption(CHROME_DEBUGGER_ADDRESS_KEY, startBrowserResponse.data.ws.selenium)
    val driver = ChromeDriver(chromeOptions)
    driver.manage().window().maximize()
    while (!currentProcessedAdsPowerScriptData?.scriptsQueueList?.isEmpty!!)
        when (currentProcessedAdsPowerScriptData?.scriptsQueueList?.dequeue()) {
            ScriptType.Overlay -> OverlayScript(driver).start()
            else -> {}
        }
}

fun getCurrentProcessedAdsPowerScriptDataTest() = currentProcessedAdsPowerScriptData