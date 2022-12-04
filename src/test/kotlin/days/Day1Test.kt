package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day1Test {

    private val day = Day1()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(24000))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(45000))
    }
}
