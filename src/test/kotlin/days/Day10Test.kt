package days

import common.Coordinates
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day10Test {
    private val day = Day10()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(13140))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(1))
    }

    @Test
    fun simpleProgram() {
        val sut = CPU()

        listOf((Command.noop to 0), (Command.addx to 3), (Command.addx to -5))
            .forEach { sut.runCommand(it.first, it.second) }

        assertThat(sut.register, `is`(-1))

    }


}
