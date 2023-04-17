package me.darksidecode.communications

import java.awt.Color
import java.awt.Font
import java.awt.Image
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

interface CountryInfo {
    fun fill(contents: JPanel) // `contents` has layout `MigLayout`

    fun title(contents: JPanel, title: String) {
        val lbl1 = JLabel(title)
        lbl1.font = Font("Segoe UI", Font.PLAIN, 24)
        lbl1.foreground = Color(150, 150, 150)
        contents.add(lbl1, "wrap")
    }

    fun htmlText(contents: JPanel, htmlText: String) {
        val lbl2 = JLabel("<html>$htmlText</html>")
        lbl2.font = Font("Segoe UI", Font.PLAIN, 16)
        contents.add(lbl2, "wrap")
    }

    fun image(contents: JPanel, imageFileName: String) {
        var imageIcon = ImageIcon(javaClass.getResource("/$imageFileName"))
        var image = imageIcon.image
        image = image.getScaledInstance(250, 200, Image.SCALE_DEFAULT)
        imageIcon = ImageIcon(image)
        contents.add(JLabel(imageIcon), "wrap")
    }
}

object Germany : CountryInfo {
    override fun fill(contents: JPanel) {
        title(contents, "Германия")

        htmlText(contents, """
            <b>Жмут руки</b> при приветствии <p>
            как мужчины, так и женщины <p>
        """.trimIndent())

        htmlText(contents, """
            Осторожнее с <b>постукиванием себя по лбу</b>! <p>
            Так немцы говорят собеседнику: <p>
            <i>"Ты что, с ума сошёл?"</i> <p>
        """.trimIndent())
    }
}

object Japan : CountryInfo {
    override fun fill(contents: JPanel) {
        title(contents, "Япония")

        htmlText(contents, """
            Любой <b>физический контакт</b> считается <p>
            невежливым. Жать руки не надо - вместо <p>
            этого при встрече японцы кланяются. <p>
        """.trimIndent())

        htmlText(contents, """
            <b>Кивок</b> - просто знак вежливости, не согласия. <p>
        """.trimIndent())

        htmlText(contents, """
            <b>Смотреть в глаза</b> не принято. <p>
        """.trimIndent())
    }
}

object France : CountryInfo {
    override fun fill(contents: JPanel) {
        title(contents, "Франция")

        htmlText(contents, """
            <b>Пожимают плечами</b> французы не когда <p>
            они растеряны, а когда хотят сказать: <p>
            <i>"Я возмущен, и наплевать мне на это"</i> <p>
        """.trimIndent())
    }
}

object Italy : CountryInfo {
    override fun fill(contents: JPanel) {
        title(contents, "Италия")

        htmlText(contents, """
            Жесты крайне сильно разнятся в разных <p>
            частях страны, особенно на Севере и Юге. <p>
        """.trimIndent())

        htmlText(contents, """
            <b>Che vuoi</b> - пожалуй, самый известный <p>
            итальянский жест - соединение кончиков всех <p>
            пальцев в конус, направленный вверх. Этим <p>
            жестом итальянцы выражают недоверие или <p>
            высмеивают сказанное другим человеком. <p>
        """.trimIndent())

        htmlText(contents, """
            Жест <b>"коза"</b> у итальянцев оскорбительный, <p>
            в отличие от Испании и некоторых стран <p>
            Латинской Америки. <p>
        """.trimIndent())

        htmlText(contents, """
            Итальянцы складывают указательные пальцы <p>
            в <b>крест на уровне рта</b>, когда хотят <p>
            уверить собеседника в своей искренности.
        """.trimIndent())
    }
}
