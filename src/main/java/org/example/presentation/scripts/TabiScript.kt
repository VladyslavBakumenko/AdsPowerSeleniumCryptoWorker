package org.example.presentation.scripts

import org.openqa.selenium.chrome.ChromeDriver

class TabiScript(private val driver: ChromeDriver) {

    fun start() {
        driver.get("https://tabi.lol/")
        Thread.sleep(10000)
    }
}