package rltut

import asciiPanel.AsciiPanel
import java.awt.Color

enum class Tile(private val glyph: Char, private val color: Color) {
    FLOOR(250.toChar(), AsciiPanel.yellow), WALL(177.toChar(), AsciiPanel.yellow), BOUNDS('x', AsciiPanel.brightBlack);

    fun glyph(): Char {
        return glyph
    }

    fun color(): Color {
        return color
    }

    fun isDiggable()= this == WALL

    fun isGround()= this != WALL && this != BOUNDS
}