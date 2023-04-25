package me.darksidecode.communications

import net.miginfocom.swing.MigLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JWindow

private const val PopupWidth: Int = 390
private const val PopupHeight: Int = 420

class CountryPopup(
    countryInfo: CountryInfo,
    prefX: Int,
    prefY: Int,
    private val parentPos: Point,
    private val parentSize: Dimension,
    private val handleMouseOver: (Boolean) -> Unit,
) : JWindow() {
    private val contents = JPanel(MigLayout())

    init {
        val scrollPane = JScrollPane(contents)
        countryInfo.fill(contents)
        add(scrollPane)
        size = Dimension(PopupWidth, PopupHeight)

        val mouseOverTrigger = object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent) {
                handleMouseOver(true)
            }
            override fun mouseExited(e: MouseEvent) {
                handleMouseOver(false)
            }
        }

        scrollPane.viewport.addMouseListener(mouseOverTrigger)
        scrollPane.horizontalScrollBar.addMouseListener(mouseOverTrigger)
        scrollPane.verticalScrollBar.addMouseListener(mouseOverTrigger)

        reposition(prefX, prefY)
        contents.background = Color(0, 0, 0, 100)
        background = Color(0, 0, 0, 100)
    }

    fun reposition(prefX: Int, prefY: Int) {
        val rightBorder = parentPos.x + parentSize.width - 10
        val popupX = when {
            prefX < parentPos.x -> parentPos.x
            prefX + PopupWidth > rightBorder -> rightBorder - PopupWidth
            else -> prefX
        }

        val bottomBorder = parentPos.y + parentSize.height - 10
        val popupY = when {
            prefY < parentPos.y -> parentPos.y
            prefY + PopupHeight > bottomBorder -> bottomBorder - PopupHeight
            else -> prefY
        }

        setLocation(popupX, popupY)
    }
}
