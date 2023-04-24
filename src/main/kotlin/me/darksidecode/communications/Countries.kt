package me.darksidecode.communications

import java.awt.Color
import java.awt.Font
import java.awt.Image
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel

interface CountryInfo {
    val name: String

    fun fill(contents: JPanel) // `contents` has layout `MigLayout`

    fun title(contents: JPanel) {
        val lbl1 = JLabel(name)
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
    override val name = "Германия"

    override fun fill(contents: JPanel) {
        title(contents)

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
    override val name = "Япония"

    override fun fill(contents: JPanel) {
        title(contents)

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

        htmlText(contents, """
            <b>Указывать пальцем</b> на объекты считается<p>
            невежливым. Вместо этого для указания<p>
            используют открытую ладонь.<p>
        """.trimIndent())
    }
}

object France : CountryInfo {
    override val name = "Франция"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            <b>Пожимают плечами</b> французы не когда <p>
            они растеряны, а когда хотят сказать: <p>
            <i>"Я возмущен, и наплевать мне на это"</i> <p>
        """.trimIndent())

        htmlText(contents, """"
            Считается вежливым поддерживать<p>
            <b>зрительный контакт</b> во время<p>
            деловых переговоров и обычных разговоров. <p>
        """.trimMargin())
    }
}

object Italy : CountryInfo {
    override val name = "Италия"

    override fun fill(contents: JPanel) {
        title(contents)

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
            уверить собеседника в своей искренности.<p>
        """.trimIndent())
    }
}

object Britain : CountryInfo{
    override val name = "Великобритания"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            <b>V-знак</b>, обращенный ладонью к себе,<p>
             считается оскорбительным.<p>
        """.trimIndent())
    }
}

object Greece : CountryInfo{
    override val name = "Греция"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
              Чтобы сказать <i>«нет»</i>, нужно один<p>
              раз <b>наклонить голову</b> назад. Легкий<p>
              <b>кивок</b> головы вперед означает <i>«да»</i>.<p>
        """.trimIndent())

        htmlText(contents, """
            В некоторых местах Греции <b>жест «Окей»</b><p>
            считается непристойным. Вместо этого<p>
            используетсяподнятый вверх большой палец.<p>
        """.trimIndent())
    }
}

object China : CountryInfo{
    override val name = "Китай"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            <b>Указание мизинцем</b> на кого-то - это<p>
            легкая форма отвращения к человеку или<p>
            тому, что он сказал.<p>
        """.trimIndent())

        htmlText(contents, """
            Считается вежливым принимать предметы<p>
            двумя руками, особенно от кого-то старше<p>
            по статусу.<p>
        """.trimIndent())

        htmlText(contents, """
            Избегайте большого количества <b>движений</b><p>
            <b>руками</b>. Они считаются невежливыми и<p>
            отвлекающими, особенно в формальной<p>
            обстановке.<p>
        """.trimIndent())

        htmlText(contents, """
            Прикосновения и физическая привязанность<p>
            не распространены. Однако <b>рукопожатия</b><p>
            являются общепринятой формой приветствия<p>
            и обычно используются во время знакомств<p>
            и деловых встреч.
        """.trimIndent())
    }
}
