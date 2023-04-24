package me.darksidecode.communications

import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JDialog
import javax.swing.JPanel
import javax.swing.JScrollPane

private const val PopupWidth = 375
private const val PopupHeight = 400

class CountryPopup(
    countryInfo: CountryInfo,
    prefX: Int,
    prefY: Int,
    parentPos: Point,
    parentSize: Dimension,
    val hoverStateChanged: () -> Unit,
) : JDialog() {
    private val contents = JPanel(MigLayout())

    @Volatile
    var isHovered = false
        private set

    init {
        countryInfo.fill(contents)
        add(JScrollPane(contents))
        size = Dimension(PopupWidth, PopupHeight)

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

        contents.addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent) {
                isHovered = true
                hoverStateChanged()
            }
            override fun mouseExited(e: MouseEvent) {
                isHovered = false
                hoverStateChanged()
            }
        })

        setLocation(popupX, popupY)

        isUndecorated = true
        isVisible = true
    }
}
