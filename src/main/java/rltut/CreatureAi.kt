package rltut

fun CreatureAi.register(creature: Creature) {
    creature.setCreatureAi(this)
}

open class CreatureAi(creature: Creature) {
    init {
        register(creature)
    }

    open fun onEnter(x: Int, y: Int, tile: Tile) { }
}