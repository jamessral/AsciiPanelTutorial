package rltut.screens

import asciiPanel.AsciiPanel
import rltut.Creature
import rltut.CreatureFactory
import rltut.World
import rltut.WorldBuilder
import java.awt.event.KeyEvent

class PlayScreen : Screen {
    private val world: World
    private var player: Creature
    private val screenWidth = 80
    private val screenHeight = 21

    init {
        world = createWorld()
        player = CreatureFactory(world).newPlayer()
    }

    override fun displayOutput(terminal: AsciiPanel) {
        val left = scrollX
        val top = scrollY
        displayTiles(terminal, left, top)
        terminal.write(player.glyph, player.x - left, player.y - top, player.color)
    }

    override fun respondToUserInput(key: KeyEvent): Screen {
        when (key.keyCode) {
            KeyEvent.VK_ESCAPE -> return LoseScreen()
            KeyEvent.VK_ENTER -> return WinScreen()
            KeyEvent.VK_LEFT, KeyEvent.VK_H -> player.moveBy(-1, 0)
            KeyEvent.VK_RIGHT, KeyEvent.VK_L -> player.moveBy(1, 0)
            KeyEvent.VK_UP, KeyEvent.VK_K -> player.moveBy(0, -1)
            KeyEvent.VK_DOWN, KeyEvent.VK_J -> player.moveBy(0, 1)
            KeyEvent.VK_Y -> player.moveBy(-1, -1)
            KeyEvent.VK_U -> player.moveBy(1, -1)
            KeyEvent.VK_B -> player.moveBy(-1, 1)
            KeyEvent.VK_N -> player.moveBy(1, 1)
        }
        return this
    }

    private val scrollX: Int
        get() = 0.coerceAtLeast((player.x - screenWidth / 2)
                 .coerceAtMost(world.width() - screenWidth))

    private val scrollY: Int
        get() = 0.coerceAtLeast((player.y - screenHeight / 2)
                 .coerceAtMost(world.height() - screenHeight))

    private fun createWorld(): World {
        return WorldBuilder(90, 31)
                .makeCaves()
                .build()
    }

    private fun displayTiles(terminal: AsciiPanel, left: Int, top: Int) {
        for (x in 0 until screenWidth) {
            for (y in 0 until screenHeight) {
                val wx = x + left
                val wy = y + top
                terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy))
            }
        }
    }
}