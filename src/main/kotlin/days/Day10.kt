package days

class Day10 : Day(10) {
    override fun partOne(): Any {
        val cpu = CPU()
        inputList.map { it.split(" ") }
            .map { Command.valueOf(it.first()) to if (it.first() == "addx") it.last().toInt() else 0 }
            .forEach { cpu.runCommand(it.first, it.second) }

        println("CPU signal strength logs: ${cpu.signalStrengthLog}")
        return cpu.signalStrengthLog.sumOf { it.first * it.second }
    }

    override fun partTwo(): Any {
        val cpu = CPU()
        inputList.map { it.split(" ") }
            .map { Command.valueOf(it.first()) to if (it.first() == "addx") it.last().toInt() else 0 }
            .forEach { cpu.runCommand(it.first, it.second) }

        val crt = CRT(List(6) { mutableListOf<Char>() })
        crt.buildImage(cpu.registerLog)

        println("CRT image:")
        crt.screen.forEach { println(it.joinToString("")) }

        return crt.screen
    }
}

data class CPU(var register: Int = 1, var cycles: Int = 1) {

    val signalStrengthLog = mutableListOf<Pair<Int, Int>>()

    val registerLog = mutableListOf<Int>()

    fun runCommand(command: Command, value: Int = 0) {
        for (i in 0 until command.cycles) {
            logSignalStrength()
            logRegister()
            cycles += 1
        }

        if (command == Command.addx) {
            register += value
        }
    }

    private fun logSignalStrength() =
        if (cycles == 20 || (signalStrengthLog.isNotEmpty() && cycles == signalStrengthLog.last().first + 40))
            signalStrengthLog.add(cycles to register)
        else
            false


    private fun logRegister() = registerLog.add(register)
}

data class CRT(val screen: List<MutableList<Char>>) {

    private var spritePosition = 0..2

    fun buildImage(cpuRegisterLog: List<Int>) {
        cpuRegisterLog.chunked(40)
            .forEachIndexed(::buildRow)

    }

    private fun buildRow(row: Int, cpuRegisterLog: List<Int>) {
        cpuRegisterLog.forEachIndexed { index, register ->
            updateSpritePosition(register)
            screen[row].add(if (index in spritePosition) '#' else '.')
        }
    }

    private fun updateSpritePosition(register: Int) {
        spritePosition = register - 1..register + 1
    }

}

enum class Command(val cycles: Int) {
    noop(1), addx(2)
}
