package org.example.presentation.scripts

import org.example.domain.utils.extensions.moveMouseAndClick
import org.example.data.utils.dataClasses.MouseClickCoordinates
import org.example.domain.utils.extensions.copyText
import org.example.domain.utils.extensions.pasteText

class UnlockMetaMaskScript(private val mataMaskPassword: String) {
    private val metaMuskPasswordInputFieldCoordinates = MouseClickCoordinates(1179, 386)
    private val metaMuskUnlockButtonCoordinates = MouseClickCoordinates(1238, 470)

    fun start() {
        repeat(3) {
            moveMouseAndClick(metaMuskPasswordInputFieldCoordinates)
            Thread.sleep(300)
        }
        copyText(mataMaskPassword)
        Thread.sleep(200)
        pasteText()
        Thread.sleep(200)
        moveMouseAndClick(metaMuskUnlockButtonCoordinates)
    }
}