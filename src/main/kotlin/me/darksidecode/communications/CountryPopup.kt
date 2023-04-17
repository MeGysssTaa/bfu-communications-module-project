package me.darksidecode.communications

import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.awt.Point
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
) : JDialog() {
    private val contents = JPanel(MigLayout())

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

        setLocation(popupX, popupY)

        isUndecorated = true
        isVisible = true
    }
}
