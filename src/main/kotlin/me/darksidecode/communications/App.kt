package me.darksidecode.communications

import com.formdev.flatlaf.themes.FlatMacLightLaf

fun main() {
    FlatMacLightLaf.setup()
    App.run()
}

object App {
    fun run() {
        MainForm()
    }
}
