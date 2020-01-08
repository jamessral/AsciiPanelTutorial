package rltut

import asciiPanel.AsciiPanel
import rltut.screens.Screen
import rltut.screens.StartScreen
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame

class ApplicationMain : JFrame(), KeyListener {
    private val terminal = AsciiPanel()
    private var screen: Screen = StartScreen()

    init {
        add(terminal)
        pack()
        addKeyListener(this)
        repaint()
    }

    override fun repaint() {
        terminal.clear()
        screen.displayOutput(terminal)
        super.repaint()
    }

    override fun keyPressed(e: KeyEvent) {
        screen = screen.respondToUserInput(e)
        repaint()
    }
    override fun keyReleased(e: KeyEvent) {}

    override fun keyTyped(e: KeyEvent) {}
    companion object {
        private const val serialVersionUID = 1060623638149583738L
        @JvmStatic
        fun main(args: Array<String>) {
            val app = ApplicationMain()
            app.defaultCloseOperation = EXIT_ON_CLOSE
            app.isVisible = true
        }

    }
}