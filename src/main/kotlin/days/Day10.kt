package days

class Day10 : Day(10) {
    override fun partOne(): Any {
        val cpu = CPU()
        inputList.map { it.split(" ") }
            .map { Command.valueOf(it.first()) to if (it.first() == "addx") it.last().toInt() else 0 }
            .forEach { cpu.runCommand(it.first, it.second) }

        println("CPU logs: ${cpu.log}")
        return cpu.log.sumOf { it.first * it.second }
    }

    override fun partTwo(): Any {
        return ""
    }
}

data class CPU(var register: Int = 1, var cycles: Int = 1) {

    val log = mutableListOf<Pair<Int, Int>>()

    fun runCommand(command: Command, value: Int = 0) {
        for (i in 0 until command.cycles) {
            println("Command: $command,$value === Cycles: $cycles === Register: $register")
            logSignalStrength()
            cycles += 1
        }

        if (command == Command.addx) {
            register += value
        }
    }

    fun logSignalStrength() {
        if (cycles == 20 || (log.isNotEmpty() && cycles == log.last().first + 40))
            log.add(cycles to register)
    }
}


enum class Command(val cycles: Int) {
    noop(1), addx(2)
}
