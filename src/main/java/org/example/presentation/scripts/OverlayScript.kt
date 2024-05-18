package org.example.presentation.scripts

import org.example.data.utils.dataClasses.MouseClickCoordinates
import org.example.domain.utils.extensions.clickOnWebElementWithDelay
import org.example.domain.utils.extensions.closeAllOtherPages
import org.example.domain.utils.extensions.moveMouseAndClick
import org.example.presentation.getCurrentProcessedAdsPowerScriptDataTest
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class OverlayScript(private val driver: ChromeDriver) {

    private lateinit var connectWalletButton: WebElement
    private lateinit var connectMetaMuskButton: WebElement

    private lateinit var ethDominance: WebElement
    private lateinit var quantumCats: WebElement
    private lateinit var btcDominance: WebElement
    private lateinit var counterStrikeSkins: WebElement
    private lateinit var btcFrogs: WebElement
    private lateinit var inc: WebElement
    private lateinit var nodeMonkes: WebElement

    private lateinit var leverageSlider: WebElement
    private lateinit var amountOvl: WebElement
    private lateinit var shortButton: WebElement
    private lateinit var approveOVButton: WebElement
    private lateinit var marketsButton: WebElement

    private lateinit var openPositionNumberWebElement: WebElement
    private lateinit var selectPositionToCloseButton: WebElement
    private lateinit var closeAllPositionCheckBox: WebElement
    private lateinit var closePositionButton: WebElement
    private lateinit var confirmClosePositionButton: WebElement

    fun start() {
        driver.get("https://app.overlay.market/#/markets")
        closeAllOtherPages(driver)
        Thread.sleep(10000)
        connectWallet()
        startTransactionsLoop()
        closePosition()
    }

    private fun connectWallet() {
        println()
        initConnectWalletButton()
        clickOnWebElementWithDelay(connectWalletButton)
        initConnectMetaMuskButton()
        clickOnWebElementWithDelay(connectMetaMuskButton)
        getCurrentProcessedAdsPowerScriptDataTest()?.adsPowerProfileData?.metaMaskPassword?.let {
            UnlockMetaMaskScript(it).start()
        }
    }

    private fun startTransactionsLoop() {
        repeat(Random.nextInt(50, 150)) {
            buildTransaction()
            driver.get("https://app.overlay.market/#/markets")
            Thread.sleep(Random.nextLong(10000, 20000))
        }
    }

    private fun buildTransaction() {
        initWebElements()
        val correctWorkingMarketList = listOf(ethDominance, btcFrogs, inc, nodeMonkes)
        clickOnWebElementWithDelay(correctWorkingMarketList[Random.nextInt(0, correctWorkingMarketList.size)])
        initBuildingPositionPageWebElements()
        setPositionFields()
        initBuildingPositionApproveOVButton()
        clickOnWebElementWithDelay(approveOVButton)
        initConfirmBuildButton()
        clickOnWebElementWithDelay(approveOVButton)
        Thread.sleep(2000)
        repeat(5) {
            moveMouseAndClick(MouseClickCoordinates(1336, 604))
        }
    }

    private fun setPositionFields() {
        if (Random.nextInt(1, 100) < 50) clickOnWebElementWithDelay(shortButton)
        val roundedOvlValue = BigDecimal(Random.nextDouble(0.1, 0.5)).setScale(1, RoundingMode.HALF_UP).toDouble()
        amountOvl.sendKeys(roundedOvlValue.toString())
        moveMouseAndClick(MouseClickCoordinates(Random.nextInt(1074, 1297), 538))
    }

    private fun closePosition() {
        driver.get("https://app.overlay.market/#/positions")
        Thread.sleep(5000)
        initClosePositionPage()
        clickOnWebElementWithDelay(selectPositionToCloseButton)
        initCloseAllPositionCheckBox()
        clickOnWebElementWithDelay(closeAllPositionCheckBox)
        initClosePositionButton()
        clickOnWebElementWithDelay(closePositionButton)
        initConfirmClosePositionButton()
        clickOnWebElementWithDelay(confirmClosePositionButton)
        val openPositionNumber = openPositionNumberWebElement.text.toInt()
        val test = openPositionNumber - Random.nextInt(openPositionNumber / 100 * 70, openPositionNumber)
        repeat(test) {
            repeat(2) {
                moveMouseAndClick(MouseClickCoordinates(1384, 592))
            }
        }
    }

    private fun initConnectWalletButton() {
        connectWalletButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/button"))
    }

    private fun initConnectMetaMuskButton() {
        connectMetaMuskButton = driver.findElement(By.xpath("//*[@id=\"connect-METAMASK\"]"))
    }

    private fun initWebElements() {
        ethDominance = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[1]"))
        quantumCats = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[2]"))
        btcDominance = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[3]"))
        counterStrikeSkins = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[4]"))
        btcFrogs = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[5]"))
        inc = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[6]"))
        nodeMonkes = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/table/tbody/tr[7]"))
    }

    private fun initBuildingPositionPageWebElements() {
        leverageSlider = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[2]/form/div[3]/input"))
        amountOvl = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[2]/form/div[4]/div[2]/input"))
        shortButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[2]/form/div[2]/button[2]"))
        marketsButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/a[1]/div"))
    }

    private fun initBuildingPositionApproveOVButton() {
        approveOVButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[2]/div[1]/button"))
    }

    private fun initConfirmBuildButton() {
        approveOVButton = driver.findElement(By.xpath("/html/body/reach-portal/div[2]/div/div/div/div/button"))
    }

    private fun initClosePositionPage() {
        openPositionNumberWebElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[2]/div/div[3]/div/div[2]"))
        selectPositionToCloseButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/div[1]/button"))
    }

    private fun initCloseAllPositionCheckBox() {
        closeAllPositionCheckBox = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/table/thead/tr/th[1]/span/span"))
    }

    private fun initClosePositionButton() {
        closePositionButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/div[1]/div[2]/button[2]"))
    }

    private fun initConfirmClosePositionButton() {
        confirmClosePositionButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/div[3]/div/div[4]/button[2]"))
    }
}