package rltut

class WorldBuilder(private val width: Int, private val height: Int) {
    private var tiles: Array<Array<Tile?>>
    fun build(): World {
        return World(tiles)
    }

    private fun randomizeTiles(): WorldBuilder {
        for (x in 0 until width) {
            for (y in 0 until height) {
                tiles[x][y] = if (Math.random() < 0.5) Tile.FLOOR else Tile.WALL
            }
        }
        return this
    }

    private fun smooth(times: Int): WorldBuilder {
        val tiles2 = Array(width) { arrayOfNulls<Tile>(height) }
        for (time in 0 until times) {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    var floors = 0
                    var rocks = 0
                    for (ox in -1..1) {
                        for (oy in -1..1) {
                            if (x + ox < 0 || x + ox >= width || y + oy < 0 || y + oy >= height) {
                                continue
                            }
                            if (tiles[x + ox][y + oy] === Tile.FLOOR) {
                                floors++
                            } else {
                                rocks++
                            }
                        }
                    }
                    tiles2[x][y] = if (floors >= rocks) Tile.FLOOR else Tile.WALL
                }
            }
            tiles = tiles2
        }
        return this
    }

    fun makeCaves(): WorldBuilder {
        return randomizeTiles().smooth(8)
    }

    init {
        tiles = Array(width) { arrayOfNulls<Tile>(height) }
    }
}