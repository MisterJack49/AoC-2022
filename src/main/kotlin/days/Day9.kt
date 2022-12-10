package days

import common.Alignement.*
import common.Coordinates
import days.Direction.*

class Day9(private val useAlternate: Boolean = false) : Day(9) {
    override fun partOne(): Any {
        val rope = Array(2) { Coordinates(0, 0) }

        val initialStep = Step(*rope)
        var simulation: Simulation = mutableListOf(initialStep)

        val input = if (useAlternate) alternateInputList else inputList
        input.map { it.split(" ") }
            .map { Direction.values().first { dir -> dir.c == it.component1() } to it.component2().toInt() }
            .forEach {
                simulation = simulation.applyMotion(it)
            }

        return simulation.map { it.knots.last() }.distinct().count()
    }

    override fun partTwo(): Any {
        val rope = Array(10) { Coordinates(0, 0) }
        val initialStep = Step(*rope)
        var simulation: Simulation = mutableListOf(initialStep)

        val input = if (useAlternate) alternateInputList else inputList

        input.map { it.split(" ") }
            .map { Direction.values().first { dir -> dir.c == it.component1() } to it.component2().toInt() }
            .forEach {
                simulation = simulation.applyMotion(it)
            }

        return simulation.map { it.knots.last() }.distinct().count()
    }
}

typealias Simulation = MutableList<Step>

typealias Motion = Pair<Direction, Int>

fun Simulation.applyMotion(motion: Motion): Simulation {
    val (direction, distance) = motion

    for (d in 1..distance) {
        val previousStep = last().knots.toList()
        val firstKnot = previousStep.first().copy()
        val afterFirst = previousStep.slice(1 until previousStep.size).toTypedArray()

        when (direction) {
            UP -> add(Step(firstKnot + Coordinates.Up, *afterFirst))
            RIGHT -> add(Step(firstKnot + Coordinates.Right, *afterFirst))
            DOWN -> add(Step(firstKnot + Coordinates.Down, *afterFirst))
            LEFT -> add(Step(firstKnot + Coordinates.Left, *afterFirst))
        }

        for (i in 1 until previousStep.size) {
            val new = last().copy()

            val currentKnot = new.knots[i].copy()
            val knotsBefore = new.knots.slice(0 until i).toTypedArray()
            val knotsAfter = if (new.knots.size - 1 == i)
                arrayOf() else new.knots.slice(i + 1 until new.knots.size).toTypedArray()

            val previousKnot = new.knots[i - 1]

            if (currentKnot.isAdjacent(previousKnot).not()) {
                when (currentKnot.getPosition(previousKnot)) {
                    listOf(ROW, BEFORE) ->
                        add(Step(*knotsBefore, currentKnot + Coordinates.Right, *knotsAfter))

                    listOf(ROW, AFTER) -> add(Step(*knotsBefore, currentKnot + Coordinates.Left, *knotsAfter))

                    listOf(COLUMN, ABOVE) -> add(Step(*knotsBefore, currentKnot + Coordinates.Down, *knotsAfter))

                    listOf(COLUMN, BELOW) -> add(Step(*knotsBefore, currentKnot + Coordinates.Up, *knotsAfter))

                    listOf(ABOVE, BEFORE) -> add(
                        Step(
                            *knotsBefore,
                            currentKnot + Coordinates.Down + Coordinates.Right,
                            *knotsAfter
                        )
                    )

                    listOf(ABOVE, AFTER) -> add(
                        Step(
                            *knotsBefore,
                            currentKnot + Coordinates.Down + Coordinates.Left,
                            *knotsAfter
                        )
                    )
                    
                    listOf(BELOW, BEFORE) -> add(
                        Step(
                            *knotsBefore,
                            currentKnot + Coordinates.Up + Coordinates.Right,
                            *knotsAfter
                        )
                    )

                    listOf(BELOW, AFTER) -> add(
                        Step(
                            *knotsBefore,
                            currentKnot + Coordinates.Up + Coordinates.Left,
                            *knotsAfter
                        )
                    )
                    
                }
            }

        }
    }
    return this
}

class Step(vararg val knots: Coordinates) {
    fun copy() = Step(*knots.map { it.copy() }.toTypedArray())
}

enum class Direction(val c: String) {
    UP("U"), RIGHT("R"), DOWN("D"), LEFT("L")
}

//^ y
//|
//+--> x
