package days

private typealias Stack = MutableList<String>

private fun Stack.removeLast(n: Int) {
    for (i in 0 until n) this.removeLast()
}

private typealias Cargo = MutableList<Stack>

private fun Cargo.topOfStacks() =
    this.filter { it.isNotEmpty() }.joinToString(separator = "") { it.last() }

class Day5 : Day(5) {
    private val stackRegex = Regex("(.{3}+)\\s?")
    private val moveRegex = Regex("move (\\d+) from (\\d+) to (\\d+)")
    private val containerRegex = Regex("([A-Z])")

    override fun partOne(): Any {
        val cargo: Cargo = buildCargo()

        buildMoves().forEach { (quantity, from, to) ->
            //reverse because container get picked one by one
            cargo[to].addAll(cargo[from].takeLast(quantity).reversed())
            cargo[from].removeLast(quantity)
        }

        return cargo.topOfStacks()
    }

    override fun partTwo(): Any {
        val cargo: Cargo = buildCargo()

        buildMoves().forEach { (quantity, from, to) ->
            cargo[to].addAll(cargo[from].takeLast(quantity))
            cargo[from].removeLast(quantity)
        }

        return cargo.topOfStacks()
    }

    private fun buildCargo(): Cargo {
        val cargo: Cargo = mutableListOf()
        val stackInput = inputList.takeWhile { it.isBlank().not() }

        for (i in 0 until stackRegex.findAll(stackInput.last()).count())
            cargo.add(mutableListOf())

        stackInput.dropLast(1).reversed().forEach {
            stackRegex.findAll(it).forEachIndexed { index, matchResult ->
                if (containerRegex.matchesAt(matchResult.value, 1))
                    cargo[index].add(containerRegex.matchAt(matchResult.value, 1)!!.destructured.component1())
            }
        }

        return cargo
    }

    private fun buildMoves() =
        inputList.takeLastWhile { it.isBlank().not() }
            .map { moveRegex.find(it)!! }
            .map {
                Triple(
                    it.destructured.component1().toInt(),
                    it.destructured.component2().toInt() - 1,
                    it.destructured.component3().toInt() - 1
                )
            }
}

