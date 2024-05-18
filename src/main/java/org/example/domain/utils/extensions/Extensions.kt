package org.example.domain.utils.extensions

import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import kotlin.random.Random

fun clickOnWebElementWithDelay(webElement: WebElement) {
    Thread.sleep(Random.nextLong(2000, 5000))
    webElement.click()
    Thread.sleep(Random.nextLong(1000, 2000))
}

fun setTextOnWebElementWithDelay(webElement: WebElement, text: String) {
    Thread.sleep(Random.nextLong(2000, 5000))
    webElement.sendKeys(text)
    Thread.sleep(Random.nextLong(1000, 2000))
}

fun closeAllOtherPages(driver: ChromeDriver) {
    val currentWindowHandle: String = driver.windowHandle
    val windowHandles: Set<String> = driver.windowHandles
    for (handle in windowHandles) {
        if (handle != currentWindowHandle) {
            driver.switchTo().window(handle)
            driver.close()
        }
    }
    driver.switchTo().window(currentWindowHandle)
}

fun switchDriverTo(driver: ChromeDriver, targetPageTitle: String) {
    val windowHandles: Set<String> = driver.windowHandles
    windowHandles.forEachIndexed { _: Int, handle: String ->
        driver.switchTo().window(handle)
        if (driver.title == targetPageTitle) return@forEachIndexed
    }
    Thread.sleep(Random.nextLong(1000, 2000))
}
