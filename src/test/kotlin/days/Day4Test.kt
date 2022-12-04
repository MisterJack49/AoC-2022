package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day4Test {

    private val day = Day4()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(2))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(4))
    }
}
