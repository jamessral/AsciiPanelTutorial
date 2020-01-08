package rltut

import asciiPanel.AsciiPanel

class CreatureFactory(private val world: World) {
    fun newPlayer(): Creature {
        val player = Creature(world, '@', AsciiPanel.brightWhite)
        world.addAtEmptyLocation(player)
        PlayerAi(player)
        return player
    }
}