package org.example.domain.utils.extensions

import kotlinx.coroutines.delay
import org.example.data.utils.dataClasses.MouseClickCoordinates
import java.awt.MouseInfo
import java.awt.event.InputEvent
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent


val robot = Robot()

fun logCurrentMouseCoordinates() {
    while (true) {
        Thread.sleep(1000)
        println(MouseInfo.getPointerInfo().location)
    }
}

fun moveMouseAndClick(mouseClickCoordinates: MouseClickCoordinates) {
    robot.mouseMove(mouseClickCoordinates.x, mouseClickCoordinates.y)
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    Thread.sleep(2000)
}

fun justClick() {
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
}

fun copyText(text: String) {
    val stringSelection = StringSelection(text)
    val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(stringSelection, null)
}

fun pasteText() {
    robot.keyPress(KeyEvent.VK_META)
    robot.keyPress(KeyEvent.VK_V)
    robot.keyRelease(KeyEvent.VK_META)
    robot.keyRelease(KeyEvent.VK_V)
}