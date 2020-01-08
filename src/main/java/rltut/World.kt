package rltut

import java.awt.Color

class World(private val tiles: Array<Array<Tile?>>) {
    private val width: Int = tiles.size
    fun width(): Int {
        return width
    }

    private val height: Int = tiles[0].size
    fun height(): Int {
        return height
    }

    fun tile(x: Int, y: Int): Tile {
        return if (x < 0 || x >= width || y < 0 || y >= height) {
            Tile.BOUNDS
        } else {
            tiles[x][y] ?: Tile.BOUNDS
        }
    }

    fun glyph(x: Int, y: Int): Char {
        return tile(x, y).glyph()
    }

    fun color(x: Int, y: Int): Color {
        return tile(x, y).color()
    }

    fun dig(x: Int, y: Int) {
        if (tile(x, y).isDiggable()) {
            tiles[x][y] = Tile.FLOOR
        }
    }

    fun addAtEmptyLocation(creature: Creature) {
        var x = (Math.random() * width).toInt()
        var y = (Math.random() * height).toInt()

        while (!tile(x, y).isGround()) {
            x = (Math.random() * width).toInt()
            y = (Math.random() * height).toInt()
        }

        creature.x = x
        creature.y = y
    }
}