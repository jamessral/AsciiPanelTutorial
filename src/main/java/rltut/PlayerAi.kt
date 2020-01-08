package rltut

class PlayerAi(private val creature: Creature) : CreatureAi(creature) {
    override fun onEnter(x: Int, y: Int, tile: Tile) {
        if (tile.isGround()) {
            creature.x = x
            creature.y = y
        } else if (tile.isDiggable()) {
            creature.dig(x, y)
        }
    }
}