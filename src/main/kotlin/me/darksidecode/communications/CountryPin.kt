package me.darksidecode.communications

import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.Timer

private const val LocationPinNormalSize: Int = 40
private const val LocationPinHoveredSize: Int = 50
private const val AutoToggleInfoOnHoverMillis: Long = 200

class CountryPin(
    pane: JPanel,
    countryInfo: CountryInfo,
    x: Int,
    y: Int,
    parentPos: Point,
    parentSize: Dimension,
) {
    private var lastMouseEntered: Long = 0L
    private var lastMouseExited: Long = 0L
    private var isOpen: Boolean = false
    private val ticker: Timer
    private var popup: CountryPopup

    init {
        popup = CountryPopup(countryInfo, x, y, parentPos, parentSize) { mouseOver ->
            val currTime = System.currentTimeMillis()
            if (mouseOver) {
                lastMouseEntered = currTime
            } else {
                lastMouseExited = currTime
            }
        }

        val hoverText = JLabel(countryInfo.name.uppercase())
        hoverText.font = Font("Segoe UI", Font.BOLD, 24)
        hoverText.foreground = Color(255, 255, 255)
        val hoverTextWidth = 10 + 20 * hoverText.text.length
        val hoverTextHeight = 24
        hoverText.setBounds(
            x - hoverTextWidth + 20,
            y + 2,
            hoverTextWidth,
            hoverTextHeight
        )
        pane.add(hoverText)
        pane.setComponentZOrder(hoverText, 0)
        hoverText.isVisible = false

        val locationPin = JLabel(locPinNormalIcon)
        locationPin.setBounds(
            x - LocationPinNormalSize / 2,
            y - LocationPinNormalSize,
            LocationPinNormalSize,
            LocationPinNormalSize
        )
        pane.add(locationPin)
        pane.setComponentZOrder(locationPin, 0)

        locationPin.addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent) {
                lastMouseEntered = System.currentTimeMillis()

                hoverText.isVisible = true
                locationPin.icon = locPinHoveredIcon
                locationPin.setBounds(
                    x - LocationPinHoveredSize / 2,
                    y - LocationPinHoveredSize,
                    LocationPinHoveredSize,
                    LocationPinHoveredSize
                )
            }

            override fun mouseExited(e: MouseEvent) {
                lastMouseExited = System.currentTimeMillis()

                hoverText.isVisible = false
                locationPin.icon = locPinNormalIcon
                locationPin.setBounds(
                    x - LocationPinNormalSize / 2,
                    y - LocationPinNormalSize,
                    LocationPinNormalSize,
                    LocationPinNormalSize
                )
            }
        })

        ticker = Timer(1) {
            val sinceEntered = System.currentTimeMillis() - lastMouseEntered
            val sinceExited = System.currentTimeMillis() - lastMouseExited
            if (!isOpen && sinceEntered in AutoToggleInfoOnHoverMillis until sinceExited) {
                open()
            } else if (isOpen && sinceExited in AutoToggleInfoOnHoverMillis until sinceEntered) {
                close()
            }
        }
    }

    fun startTicker() {
        ticker.start()
    }

    private fun open() {
        isOpen = true
        popup.isVisible = true
        lastMouseExited = 0
    }

    private fun close() {
        isOpen = false
        popup.isVisible = false
        lastMouseEntered = 0
    }

    private companion object {
        val locPinNormalIcon: ImageIcon
        val locPinHoveredIcon: ImageIcon

        init {
            val locPinImage = ImageIO.read(CountryPin::class.java.getResource("/location_pin.png"))
            locPinNormalIcon = ImageIcon(locPinImage.getScaledInstance(
                LocationPinNormalSize, LocationPinNormalSize, Image.SCALE_SMOOTH))
            locPinHoveredIcon = ImageIcon(locPinImage.getScaledInstance(
                LocationPinHoveredSize, LocationPinHoveredSize, Image.SCALE_SMOOTH))
        }
    }
}
