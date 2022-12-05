package days

private enum class Shape(val score: Int) {
    Rock(1), Paper(2), Scissors(3)
}

private enum class Outcome(val score: Int) {
    Lose(0), Draw(3), Win(6)
}

class Day2 : Day(2) {

    override fun partOne(): Any {
        return inputList.map { entry -> entry.first().toShape() to entry.last().toShape() }
            .sumOf { round -> shifumi(round.first, round.second).score + round.second.score }
    }

    override fun partTwo(): Any {
        return inputList.map { entry -> entry.first().toShape() to entry.last().toOutcome() }
            .sumOf { round -> move(round.first, round.second).score + round.second.score }
    }

    private fun shifumi(opponent: Shape, player: Shape): Outcome =
        when (opponent) {
            Shape.Rock -> if (player == Shape.Scissors) Outcome.Lose else if (player == Shape.Rock) Outcome.Draw else Outcome.Win
            Shape.Paper -> if (player == Shape.Rock) Outcome.Lose else if (player == Shape.Paper) Outcome.Draw else Outcome.Win
            Shape.Scissors -> if (player == Shape.Paper) Outcome.Lose else if (player == Shape.Scissors) Outcome.Draw else Outcome.Win
        }

    private fun move(opponent: Shape, outcome: Outcome): Shape {
        if (outcome == Outcome.Draw)
            return opponent
        return when (opponent) {
            Shape.Rock -> if (outcome == Outcome.Lose) Shape.Scissors else Shape.Paper
            Shape.Paper -> if (outcome == Outcome.Lose) Shape.Rock else Shape.Scissors
            Shape.Scissors -> if (outcome == Outcome.Lose) Shape.Paper else Shape.Rock
        }
    }

}

private fun Char.toShape() =
    when (this) {
        'A', 'X' -> Shape.Rock
        'B', 'Y' -> Shape.Paper
        'C', 'Z' -> Shape.Scissors
        else -> throw IllegalArgumentException()
    }

private fun Char.toOutcome() =
    when (this) {
        'X' -> Outcome.Lose
        'Y' -> Outcome.Draw
        'Z' -> Outcome.Win
        else -> throw IllegalArgumentException()
    }
