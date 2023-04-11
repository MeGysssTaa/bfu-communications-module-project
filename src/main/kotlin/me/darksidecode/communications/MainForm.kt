package me.darksidecode.communications

import java.awt.Dimension
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

private const val WorldMapWidth = 1100
private const val WorldMapHeight = 585

private const val LocationPinNormalSize = 40
private const val LocationPinHoveredSize = 50

class MainForm : JFrame() {
    private val pane = JPanel(null)

    private lateinit var locPinNormalIcon: ImageIcon
    private lateinit var locPinHoveredIcon: ImageIcon

    private var currentPopup: CountryPopup? = null

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Региональные особенности невербальной коммуникации"

        setupComponents()
        pane.setBounds(0, 0, WorldMapWidth, WorldMapHeight)
        pane.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                currentPopup?.dispose()
                println("click ${e.x} ${e.y} | ${e.xOnScreen} ${e.yOnScreen}")
            }
        })
        add(pane)

        size = Dimension(WorldMapWidth + 10, WorldMapHeight + 30)
        setLocationRelativeTo(null)
        isVisible = true
    }

    private fun setupComponents() {
        val worldMapImage = ImageIO
            .read(javaClass.classLoader.getResource("world_map.png"))
            .getScaledInstance(WorldMapWidth, WorldMapHeight, Image.SCALE_SMOOTH)
        val lblWorldMap = JLabel(ImageIcon(worldMapImage))
        lblWorldMap.setBounds(0, 0, WorldMapWidth, WorldMapHeight)
        pane.add(lblWorldMap)

        val locPinImage = ImageIO.read(javaClass.classLoader.getResource("location_pin.png"))
        locPinNormalIcon = ImageIcon(locPinImage.getScaledInstance(
            LocationPinNormalSize, LocationPinNormalSize, Image.SCALE_SMOOTH))
        locPinHoveredIcon = ImageIcon(locPinImage.getScaledInstance(
            LocationPinHoveredSize, LocationPinHoveredSize, Image.SCALE_SMOOTH))

        addRegionButton(Japan, 880, 230)
        addRegionButton(Australia, 900, 470)
        addRegionButton(Brazil, 380, 400)
    }

    private fun addRegionButton(countryInfo: CountryInfo, x: Int, y: Int) {
        val locationPin = JLabel(locPinNormalIcon)
        locationPin.setBounds(
            x - LocationPinNormalSize / 2,
            y - LocationPinNormalSize,
            LocationPinNormalSize,
            LocationPinNormalSize
        )
        locationPin.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                currentPopup?.dispose()
                currentPopup = CountryPopup(countryInfo, x, y, location, size)
            }

            override fun mouseEntered(e: MouseEvent) {
                locationPin.icon = locPinHoveredIcon
                locationPin.setBounds(
                    x - LocationPinHoveredSize / 2,
                    y - LocationPinHoveredSize,
                    LocationPinHoveredSize,
                    LocationPinHoveredSize
                )
            }

            override fun mouseExited(e: MouseEvent) {
                locationPin.icon = locPinNormalIcon
                locationPin.setBounds(
                    x - LocationPinNormalSize / 2,
                    y - LocationPinNormalSize,
                    LocationPinNormalSize,
                    LocationPinNormalSize
                )
            }
        })

        pane.add(locationPin)
        pane.setComponentZOrder(locationPin, 0)
    }
}
