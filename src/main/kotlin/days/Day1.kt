package days

class Day1 : Day(1) {
    override fun partOne(): Any {
        return inputString.split("\r\n\r\n")
            .map { inventories -> inventories.split("\r\n") }
            .maxOf { inventory -> inventory.map { it.toInt() }.reduce { acc, x -> acc + x } }
    }

    override fun partTwo(): Any {
        return inputString.split("\r\n\r\n")
            .map { inventories -> inventories.split("\r\n") }
            .map { inventory -> inventory.map { it.toInt() }.reduce { acc, x -> acc + x } }
            .sorted()
            .takeLast(3)
            .sum()
    }
}
