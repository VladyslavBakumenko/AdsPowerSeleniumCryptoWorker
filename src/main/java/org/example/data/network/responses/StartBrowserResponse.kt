package org.example.data.network.responses

import com.google.gson.annotations.SerializedName

data class StartBrowserResponse(
        @SerializedName("code") var code: Int? = null,
        @SerializedName("data") var data: StartBrowserData,
        @SerializedName("msg") var msg: String? = null)

data class StartBrowserData(
        @SerializedName("ws") var ws: StartBrowserWS,
        @SerializedName("debug_port") var debugPort: String? = null,
        @SerializedName("webdriver") var webdriver: String)

data class StartBrowserWS(
        @SerializedName("selenium") var selenium: String,
        @SerializedName("puppeteer") var puppeteer: String? = null)