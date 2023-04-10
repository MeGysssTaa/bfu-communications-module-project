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

const val WorldMapWidth = 1100
const val WorldMapHeight = 585

const val LocationPinSize = 48

class MainForm : JFrame() {
    private val pane = JPanel(null)

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Региональные особенности невербальной коммуникации"

        setupComponents()
        pane.setBounds(0, 0, WorldMapWidth, WorldMapHeight)
        pane.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) = println("click ${e.x} ${e.y} | ${e.xOnScreen} ${e.yOnScreen}")
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

        addRegionButton(880, 230) { println("click japan") }
    }

    private fun addRegionButton(x: Int, y: Int, buttonClicked: () -> Unit) {
        val locPinImage = ImageIO
            .read(javaClass.classLoader.getResource("location_pin.png"))
            .getScaledInstance(LocationPinSize, LocationPinSize, Image.SCALE_SMOOTH)
        val lblBtn = JLabel(ImageIcon(locPinImage))
        lblBtn.setBounds(x - LocationPinSize / 2, y - LocationPinSize, LocationPinSize, LocationPinSize)
        lblBtn.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) = buttonClicked()
        })
        pane.add(lblBtn)
        pane.setComponentZOrder(lblBtn, 0)
    }
}
