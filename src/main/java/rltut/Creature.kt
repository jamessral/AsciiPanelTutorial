package rltut

import java.awt.Color

class Creature(var world: World, var glyph: Char, var color: Color) {
    var x: Int = 0
    var y: Int = 0

    private lateinit var ai: CreatureAi
    fun setCreatureAi(ai: CreatureAi) {
        this.ai = ai
    }

    fun dig(wx: Int, wy: Int) {
        world.dig(wx, wy)
    }

    fun moveBy(mx: Int, my: Int) {
        ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my))
    }
}