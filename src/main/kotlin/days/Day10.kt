package days

class Day10 : Day(10) {
    override fun partOne(): Any {
        TODO("Not yet implemented")
    }

    override fun partTwo(): Any {
        TODO("Not yet implemented")
    }

    fun runProgram() {

    }
}

data class CPU(var register: Int = 1, var cycles: Int)

enum class Command(val cycles: Int) {
    noop(1), addx(2)
}
