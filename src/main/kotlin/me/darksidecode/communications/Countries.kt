package me.darksidecode.communications

import java.awt.Color
import java.awt.Font
import java.awt.Image
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSeparator

interface CountryInfo {
    val name: String

    fun fill(contents: JPanel) // `contents` has layout `MigLayout`

    fun title(contents: JPanel) {
        val lbl = JLabel(name)
        lbl.font = Font("Segoe UI", Font.PLAIN, 24)
        lbl.foreground = Color(180, 180, 180)
        contents.add(lbl, "wrap")
    }

    fun htmlText(contents: JPanel, htmlText: String) {
        val lbl = JLabel("<html>$htmlText</html>")
        lbl.font = Font("Segoe UI", Font.PLAIN, 16)
        lbl.foreground = Color(225, 225, 225)
        contents.add(lbl, "wrap")
    }

    fun image(contents: JPanel, imageFileName: String, width: Int) {
        var imageIcon = ImageIcon(javaClass.getResource("/$imageFileName"))
        var image = imageIcon.image
        image = image.getScaledInstance(width, imageIcon.iconHeight * width / imageIcon.iconWidth, Image.SCALE_DEFAULT)
        imageIcon = ImageIcon(image)
        contents.add(JLabel(imageIcon), "wrap")
    }

    fun sep(contents: JPanel) {
        val sep = JSeparator()
        sep.foreground = Color(125, 125, 125)
        contents.add(sep, "wrap, w 350!, h 10!")
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
        sep(contents)

        htmlText(contents, """
            Осторожнее с <b>постукиванием себя по лбу</b>! <p>
            Так немцы говорят собеседнику: <p>
            <i>"Ты что, с ума сошёл?"</i> <p>
        """.trimIndent())

        image(contents, "germany_tapping_head.gif", 370)
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
        image(contents, "japan_bow.jpg", 355)
        sep(contents)

        htmlText(contents, """
            <b>Кивок</b> - просто знак вежливости, не согласия. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            <b>Смотреть в глаза</b> не принято. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            <b>Указывать пальцем</b> на объекты считается <p>
            невежливым. Вместо этого для указания <p>
            используют открытую ладонь. <p>
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
        image(contents, "france_shrug.jpg", 370)
        sep(contents)

        htmlText(contents, """
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
        sep(contents)

        htmlText(contents, """
            <b>Che vuoi</b> - пожалуй, самый известный <p>
            итальянский жест - соединение кончиков всех <p>
            пальцев в конус, направленный вверх. Этим <p>
            жестом итальянцы выражают недоверие или <p>
            высмеивают сказанное другим человеком. <p>
        """.trimIndent())
        image(contents, "italy_hand.jpg", 355)
        sep(contents)

        htmlText(contents, """
            Жест <b>"коза"</b> у итальянцев оскорбительный, <p>
            в отличие от Испании и некоторых стран <p>
            Латинской Америки. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Итальянцы складывают указательные пальцы <p>
            в <b>крест на уровне рта</b>, когда хотят <p>
            уверить собеседника в своей искренности.<p>
        """.trimIndent())
        image(contents, "italy_finger_cross.jpg", 355)
    }
}

object Britain : CountryInfo {
    override val name = "Великобритания"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            У британцев принято <b>спокойное положение</b><p>
            <b>рук</b> на виду: излишняя жестикуляция или руки<p>
            в карманах считаются дурным тоном. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Скудно используют <b>мимику</b>, но <p>
            часто <b>улыбаются</b>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            <b>V-знак</b>, обращенный ладонью к себе, <p>
             считается оскорбительным. <p>
        """.trimIndent())
        image(contents, "britain_v.jpg", 355)
        sep(contents)

        htmlText(contents, """
            Чтобы дать собеседнику понять, что вы <p>
            его слушаете, кивать не нужно — <p>
            просто <b>моргайте</b>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Британцы избегают <b>физического контакта</b>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            <b>Покрутить пальцем у виска</b> — <p> 
            предложить человеку решить самому. <p>
        """.trimIndent())
        image(contents, "britain_temple.jpg", 355)
    }
}

object Greece : CountryInfo {
    override val name = "Греция"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
              Чтобы сказать <i>«нет»</i>, нужно один <p>
              раз <b>наклонить голову</b> назад.<p>
        """.trimIndent())
        image(contents, "greece_no.gif", 355)
        sep(contents)

        htmlText(contents, """
            Легкий <b>кивок</b> головы вперед<p>
            означает <i>«да»</i>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Жест <b>"Мутза"</b> используется как<p>
            оскорбление и означающий презрение.<p>
            Он состоит в растопыривании всех пальцев<p>
            руки и поднесении ладони к лицу<p>
            оскорбляемого<p>
        """.trimIndent())
        image(contents, "greece_moutza.jpg", 355)
        sep(contents)

        htmlText(contents, """
            В некоторых местах Греции <b>жест «Окей»</b> <p>
            считается непристойным. Вместо этого <p>
            используется поднятый вверх большой палец. <p>
        """.trimIndent())
    }
}

object China : CountryInfo {
    override val name = "Китай"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            <b>Указание мизинцем</b> на кого-то - это <p>
            легкая форма отвращения к человеку или <p>
            тому, что он сказал. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Считается вежливым принимать предметы <p>
            двумя руками, особенно от кого-то старше <p>
            по статусу. <p>
        """.trimIndent())
        image(contents, "china_taking_gift.jpg", 355)
        sep(contents)

        htmlText(contents, """
            Избегайте большого количества <b>движений</b> <p>
            <b>руками</b>. Они считаются невежливыми и <p>
            отвлекающими, особенно в формальной <p>
            обстановке. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Прикосновения и физическая привязанность <p>
            не распространены. Однако <b>рукопожатия</b> <p>
            являются общепринятой формой приветствия <p>
            и обычно используются во время знакомств <p>
            и деловых встреч. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
             Чтобы позвать кого-то подойти к вам нужно<p>
             протянуть к человеку руку ладонью вниз,<p>
             сгибая пальцы вперед и назад.
        """.trimIndent())
        image(contents, "china_come_here.gif", 355)
    }
}

object Brazil : CountryInfo {
    override val name = "Бразилия"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            Жест <b>"окей"</b> считается оскорбительным. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Жест <b>"рога"</b> — пожелание удачи. <p>
        """.trimIndent())
        image(contents, "brazil_horns.jpg", 355)
        sep(contents)

        htmlText(contents, """
            <b>Физический контакт</b> во время общения <p>
            распространён широко. В Бразилии при <p>
            встрече часто обнимаются, касаются друг друга <p>
            за локти/плечи при длительном рукопожатии. <p>
        """.trimIndent())
    }
}

object SouthAfrica : CountryInfo {
    override val name = "ЮАР"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            Жест <b>"коза"</b> считается оскорбительным. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Широкая <b>улыбка, смех</b> в странах <p>
            Африки зачастую расценивается как признак <p>
            недоумения, замешательства. <p>
        """.trimIndent())
    }
}

object Mexico : CountryInfo {
    override val name = "Мексика"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            Во время делового общения необходимо <p>
            <b>пожать руку</b> каждому из участников <p>
            (при встрече и при расставании), а также<p>
            отдавать <p> <b>легкий поклон</b> каждому кто<p>
            входит в комнату. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            <b>Физический контакт</b> во время общения <p>
            распространён широко. В Мексике при <p>
            встрече часто обнимаются, касаются друг друга <p>
            за локти/плечи при длительном рукопожатии. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Когда хотите показать рост или высоту <p>
            кого-то (чего-то), используйте в жесте только <p>
            <b>указательный палец</b>. Не используйте <p>
            <b>всю ладонь</b> — такой жест применяется <p>
            исключительно для обозначения роста<p>
            животного. <p>
        """.trimIndent())
        image(contents, "doggo.jpg", 355)
    }
}

object Canada : CountryInfo {
    override val name = "Канада"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            Манеры канадцев сильно разнятся от части <p>
            страны. В провинции Квебек они ближе к <p>
            <b>французам</b>, а канадцы с британским <p>
            происхождением, соответственно, — к <p>
            <b>британцам</b> или <b>американцам</b>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            В Канаде принято <b>улыбаться</b>. <p>
            Почти всем и всегда, без повода. <p>
        """.trimIndent())
        image(contents, "canada_smile.jpg", 355)
    }
}

object India : CountryInfo {
    override val name = "Индия"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            При приветствии в большинстве мест Индии <p>
            принято <b>складывать ладони у груди</b> и <p>
            произносить <i>"намасте"</i>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Передавать предметы лучше через <p>
            <b>правую руку</b>. Левая в Индии <p>
            считается нечистой — передавая что-либо <p>
            через неё, вы можете оскорбить человека. <p>
        """.trimIndent())
    }
}

object Philippines : CountryInfo {
    override val name = "Филиппины"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            Если хотите кого-то позвать, <p>
            не пытайтесь <b>манить их пальцем</b>. <p>
            На Филиппинах таким жестом зовут <p>
            только кого-то гораздо ниже себя по статусу.<p>
        """.trimIndent())
    }
}

object Iran : CountryInfo {
    override val name = "Иран"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            <b>Обниматься</b> и <b>целоваться</b> <p>
            на публике — под строгим запретом.
        """.trimIndent())
        image(contents, "iran_hug.png", 300)
    }
}

object Cuba : CountryInfo {
    override val name = "Куба"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            <b>Сморкаться</b> на публике — запрещено. <p>
        """.trimIndent())
    }
}

object Turkey : CountryInfo {
    override val name = "Турция"

    override fun fill(contents: JPanel) {
        title(contents)

        htmlText(contents, """
            При общении с турками важно поддерживать <p>
            <b>зрительный контакт</b>: это знак искренности. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            <b>Щелчок по горлу</b> — предложение <p>
            поесть, а не выпить. <p>
        """.trimIndent())
        image(contents, "turkey_lets_eat.jpeg", 355)
        sep(contents)

        htmlText(contents, """
            Для выражения <b>отрицания</b> можно:
            <ul>
                <li> приподнять брови; </li>
                <li> цокнуть языком; </li>
                <li> запрокинуть голову вверх. </li>
            </ul> (достаточно чего-то одного). <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Фраза <i>"спасибо"</i> в ответ на <p>
            предложение чего-то по умолчанию — <p>
            <b>вежливый отказ</b>. Если хотите <p>
            <b>принять</b> то, что вам предложили, <p>
            используйте более явное <i>"Да, спасибо"</i>. <p>
        """.trimIndent())
        sep(contents)

        htmlText(contents, """
            Мужчины при встрече <b>жмут руки</b>, <p>
            но при прощании — нет. <p>
        """.trimIndent())
    }
}
