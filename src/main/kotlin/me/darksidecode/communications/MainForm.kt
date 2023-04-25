package me.darksidecode.communications

import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO
import javax.swing.*

private const val WorldMapWidth: Int = 1550
private const val WorldMapHeight: Int = 850

private const val LocationPinNormalSize: Int = 40
private const val LocationPinHoveredSize: Int = 50

private const val AutoToggleInfoOnHoverMillis: Long = 0L

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
                println("click ${e.x}, ${e.y}")
            }
        })
        add(pane)

        size = Dimension(WorldMapWidth + 10, WorldMapHeight + 30)
        isResizable = false
        extendedState = extendedState or MAXIMIZED_BOTH
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

        addRegionButton(Germany, 775, 300)
        addRegionButton(Japan, 1235, 335)
        addRegionButton(France, 750, 320)
        addRegionButton(Italy, 785, 340)
        addRegionButton(Britain, 727, 270)
        addRegionButton(Greece, 819, 357)
        addRegionButton(China, 1147, 319)
        addRegionButton(Brazil, 549, 568)
        addRegionButton(SouthAfrica, 827, 655)
        addRegionButton(Mexico, 359 , 420)
        addRegionButton(Canada, 355, 240)
        addRegionButton(India, 1038, 429)
        addRegionButton(Philippines, 1216, 468)
        addRegionButton(Iran, 939, 385)
        addRegionButton(Cuba, 451, 429)
    }

    private fun addRegionButton(countryInfo: CountryInfo, x: Int, y: Int) {
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
            @Volatile
            private var ourLastPopup: CountryPopup? = null

            @Volatile
            private var isPinHovered = false

            private val isHovered get() =
                isPinHovered || (currentPopup == ourLastPopup && (currentPopup?.isHovered ?: false))

            private var popupOpenTime: Long = 0L
            private val millisSincePopupOpen: Long get() = System.currentTimeMillis() - popupOpenTime

            override fun mouseClicked(e: MouseEvent) {
                showCountryInfo()
            }

            private fun showCountryInfo() {
                currentPopup?.dispose()
                ourLastPopup = CountryPopup(countryInfo, x, y, location, size) { mouseExitedCheckLater() }
                currentPopup = ourLastPopup
                popupOpenTime = System.currentTimeMillis()
            }

            override fun mouseEntered(e: MouseEvent) {
                isPinHovered = true

                hoverText.isVisible = true
                locationPin.icon = locPinHoveredIcon
                locationPin.setBounds(
                    x - LocationPinHoveredSize / 2,
                    y - LocationPinHoveredSize,
                    LocationPinHoveredSize,
                    LocationPinHoveredSize
                )

                val hoverTimer = r@ {
                    Thread.sleep(AutoToggleInfoOnHoverMillis)
                    if (!isHovered) return@r
                    SwingUtilities.invokeLater(this::showCountryInfo)
                }

                Thread(hoverTimer, "Hover Timer " + (System.nanoTime() % 1000000L)).start()
            }

            override fun mouseExited(e: MouseEvent) {
                isPinHovered = false

                hoverText.isVisible = false
                locationPin.icon = locPinNormalIcon
                locationPin.setBounds(
                    x - LocationPinNormalSize / 2,
                    y - LocationPinNormalSize,
                    LocationPinNormalSize,
                    LocationPinNormalSize
                )

                mouseExitedCheckLater()
            }

            private fun mouseExitedCheckLater() {
                if (millisSincePopupOpen < 250)
                    return

                val hoverTimer = r@ {
                    Thread.sleep(AutoToggleInfoOnHoverMillis)
                    if (isHovered) return@r
                    SwingUtilities.invokeLater { currentPopup?.dispose() }
                }

                Thread(hoverTimer, "Hover Timer " + (System.nanoTime() % 1000000L)).start()
            }
        })
    }
}
