package org.example.presentation.scripts

import org.example.data.utils.dataClasses.MouseClickCoordinates
import org.example.data.utils.enums.ScriptType
import org.example.domain.utils.extensions.clickOnWebElementWithDelay
import org.example.domain.utils.extensions.moveMouseAndClick
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import java.awt.Robot
import java.awt.event.InputEvent
import kotlin.random.Random

class PixelverseScript(private val driver: ChromeDriver) {

    private lateinit var petsPage: WebElement
    private lateinit var claimButton: WebElement
    private lateinit var zhuchokPet: WebElement
    private lateinit var alohaPet: WebElement
    private lateinit var atomPet: WebElement
    private lateinit var savePetButton: WebElement
    private lateinit var changePetButton: WebElement
    private lateinit var choosePetButton: WebElement

    private val secondPetClickCoordinates = MouseClickCoordinates(358, 384)
    private val thirdPetClickCoordinates = MouseClickCoordinates(525, 384)


    fun start() {
        driver.get("https://dashboard.pixelverse.xyz/pets")
        Thread.sleep(Random.nextLong(10000, 20000))
        driver.navigate().refresh()
        Thread.sleep(Random.nextLong(500, 3000))
        initFirstPageWebElements()

        clickOnWebElementWithDelay(petsPage)
        clickOnWebElementWithDelay(claimButton)
        clickOnWebElementWithDelay(changePetButton)
        initPetsPageWebElements()

        val notCollectedPets = listOf(secondPetClickCoordinates, thirdPetClickCoordinates)

        var counter = 0
        for (i in notCollectedPets) {
            Thread.sleep(3000)
            while (counter != 3) {
                Thread.sleep(1000)
                moveMouseAndClick(i)
                counter++
            }
            counter = 0
            choosePetButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/button[2]"))
            clickOnWebElementWithDelay(choosePetButton)
            clickOnWebElementWithDelay(savePetButton)
            Thread.sleep(Random.nextLong(15000, 30000))
            claimButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/section/div/div[2]/div/div[1]/div[3]/div[3]/button"))
            clickOnWebElementWithDelay(claimButton)
            clickOnWebElementWithDelay(petsPage)
            clickOnWebElementWithDelay(changePetButton)
        }
    }


    private fun initFirstPageWebElements() {
        petsPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div/div[1]/div/div/div[4]"))
        claimButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/section/div/div[2]/div/div[1]/div[3]/div[3]/button"))
        changePetButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/section/div/div[1]/div/div/button"))
    }

    private fun initPetsPageWebElements() {
        choosePetButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/button[2]"))
        savePetButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div[2]/button"))
    }
}