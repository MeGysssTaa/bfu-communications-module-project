package me.darksidecode.communications

import java.awt.Dimension
import java.awt.Image
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO
import javax.swing.*

private const val WorldMapWidth: Int = 1550
private const val WorldMapHeight: Int = 850

class MainForm : JFrame() {
    private val pane = JPanel(null)
    private val countryPins = mutableListOf<CountryPin>()

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Региональные особенности невербальной коммуникации"

        pane.setBounds(0, 0, WorldMapWidth, WorldMapHeight)
        pane.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                println("click ${e.x}, ${e.y}")
            }
        })
        add(pane)

        size = Dimension(WorldMapWidth + 10, WorldMapHeight + 30)
        isResizable = false
        extendedState = extendedState or MAXIMIZED_BOTH
        setLocationRelativeTo(null)

        setupComponents()

        val delayedTask = Timer(2000) {
            countryPins.forEach { it.startTicker() }
        }
        delayedTask.isRepeats = false
        delayedTask.start()

        isVisible = true
    }

    private fun setupComponents() {
        val worldMapImage = ImageIO
            .read(javaClass.classLoader.getResource("world_map.png"))
            .getScaledInstance(WorldMapWidth, WorldMapHeight, Image.SCALE_SMOOTH)
        val lblWorldMap = JLabel(ImageIcon(worldMapImage))
        lblWorldMap.setBounds(0, 0, WorldMapWidth, WorldMapHeight)
        pane.add(lblWorldMap)

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
        addRegionButton(Turkey, 865, 353)
    }

    private fun addRegionButton(countryInfo: CountryInfo, x: Int, y: Int) {
        countryPins += CountryPin(pane, countryInfo, x, y, pane.location, pane.size) { countryPins.forEach { it.close() } }
    }
}
