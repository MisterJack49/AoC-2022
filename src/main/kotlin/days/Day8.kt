package days

class Day8 : Day(8) {
    override fun partOne(): Any {
        val forest = inputList.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Tree(c.digitToInt(), Coordinates(x, y))
            }
        }.flatten()

        val innerForest = forest.getInnerForest()
        innerForest.onEach { tree ->
            forest.getTreesAround(tree.coordinates).onEach { orientation ->
                tree.isVisible = tree.isVisible || orientation.value.none { it.height >= tree.height }
            }
        }

        return forest.getEdge().count() + innerForest.count { it.isVisible }
    }

    override fun partTwo(): Any {
        val forest = inputList.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Tree(c.digitToInt(), Coordinates(x, y))
            }
        }.flatten()

        return forest.getInnerForest().onEach { tree ->
            tree.scenicScore = forest.getViewableFrom(tree).map { it.count() }.reduce { acc, i -> acc * i }
        }.maxOf { it.scenicScore }
    }
}

data class Tree(
    val height: Int,
    val coordinates: Coordinates = Coordinates(0, 0),
    var isVisible: Boolean = false,
    var scenicScore: Int = 0
) {
    fun canSee(trees: List<Tree>): List<Tree> {
        val firstBlockingTree = (trees.indexOfFirst { it.height >= this.height })
            .let { if (it == -1) trees.size - 1 else it }
        return trees.subList(0, firstBlockingTree + 1)
    }

    override fun toString(): String {
        return "$coordinates: $height"
    }
}

fun List<Tree>.getSize(): Coordinates = last().coordinates

fun List<Tree>.getEdge(): List<Tree> =
    filter {
        it.coordinates.x == 0 || it.coordinates.y == 0 ||
                it.coordinates.x == getSize().x || it.coordinates.y == getSize().y
    }.copy()

fun List<Tree>.copy() = map { it.copy() }

fun List<Tree>.getInnerForest(): List<Tree> = copy() - getEdge().toSet()

fun List<Tree>.getTreesAround(coordinates: Coordinates): Map<Orientation, List<Tree>> =
    Orientation.values().associateWith { orientation ->
        when (orientation) {
            Orientation.NORTH -> {
                filter { it.coordinates.x == coordinates.x && it.coordinates.y < coordinates.y }.copy()
            }

            Orientation.EAST -> {
                filter { it.coordinates.x > coordinates.x && it.coordinates.y == coordinates.y }.copy()
            }

            Orientation.SOUTH -> {
                filter { it.coordinates.x == coordinates.x && it.coordinates.y > coordinates.y }.copy()
            }

            Orientation.WEST -> {
                filter { it.coordinates.x < coordinates.x && it.coordinates.y == coordinates.y }.copy()
            }
        }
    
    }

fun List<Tree>.getViewableFrom(tree: Tree) =
    getTreesAround(tree.coordinates).map { (orientation, trees) ->
        when (orientation) {
            Orientation.NORTH, Orientation.WEST -> {
                tree.canSee(trees.reversed().copy())
            }

            Orientation.EAST, Orientation.SOUTH -> {
                tree.canSee(trees.copy())
            }
        }
    }

data class Coordinates(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x,$y)"
    }
}

enum class Orientation { NORTH, EAST, SOUTH, WEST }

//+---> x        N
//|              |
//|           O--+--E
//|              |
//v y            S
