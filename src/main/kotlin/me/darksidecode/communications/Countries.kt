package me.darksidecode.communications

import java.awt.Font
import java.awt.Image
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

interface CountryInfo {
    fun fill(contents: JPanel) // `contents` has layout `MigLayout`
}

object Brazil : CountryInfo {
    override fun fill(contents: JPanel) {
        val lbl1 = JLabel("Бразилия")
        lbl1.font = Font("Segoe UI", Font.BOLD, 24)
        contents.add(lbl1, "wrap")

        val img1 = ImageIO.read(javaClass.classLoader.getResource("test.png"))
            .getScaledInstance(250, 200, Image.SCALE_SMOOTH)
        contents.add(JLabel(ImageIcon(img1)), "wrap")

        val lbl2 = JLabel("<html><p>line 1<p>line <b>two</b>2 <p>sdfsdfdsdfsfs<p>sdfsdfqe<p>qeqwe<p>adasd<p>zfzxzxzc<p>qeqweqwedf</html>")
        lbl2.font = Font("Segoe UI", Font.PLAIN, 18)
        contents.add(lbl2, "wrap")
    }
}

object Japan : CountryInfo {
    override fun fill(contents: JPanel) {
        val lbl1 = JLabel("Япония")
        lbl1.font = Font("Segoe UI", Font.BOLD, 24)
        contents.add(lbl1, "wrap")

//        val img1 = ImageIO.read(javaClass.getResource("/gigacapy.gif"))
//            .getScaledInstance(250, 200, Image.SCALE_SMOOTH)

        var imageIcon = ImageIcon(javaClass.getResource("/gigacapy.gif")) // load the image to a imageIcon

        val image = imageIcon.image // transform it

        val newimg = image.getScaledInstance(250, 200, Image.SCALE_DEFAULT) // scale it the smooth way

        imageIcon = ImageIcon(newimg) // transform it back


        //val img1 = ImageIcon(javaClass.getResource("/gigacapy.gif"))
        contents.add(JLabel(imageIcon), "wrap")

        val lbl2 = JLabel("<html><p>line 1<p>line <b>two</b>2 <p>sdfsdfdsdfsfs<p>sdfsdfqe<p>qeqwe<p>adasd<p>zfzxzxzc<p>qeqweqwedf</html>")
        lbl2.font = Font("Segoe UI", Font.PLAIN, 18)
        contents.add(lbl2, "wrap")
    }
}

object Australia : CountryInfo {
    override fun fill(contents: JPanel) {
        val lbl1 = JLabel("Австралия")
        lbl1.font = Font("Segoe UI", Font.BOLD, 24)
        contents.add(lbl1, "wrap")

        val img1 = ImageIO.read(javaClass.classLoader.getResource("test.png"))
            .getScaledInstance(250, 200, Image.SCALE_SMOOTH)
        contents.add(JLabel(ImageIcon(img1)), "wrap")

        val lbl2 = JLabel("<html><p>line 1<p>line <b>two</b>2 <p>sdfsdfdsdfsfs<p>sdfsdfqe<p>qeqwe<p>adasd<p>zfzxzxzc<p>qeqweqwedf</html>")
        lbl2.font = Font("Segoe UI", Font.PLAIN, 18)
        contents.add(lbl2, "wrap")
    }
}
