package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day7Test {
    private val day = Day7()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(95437))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(24933642))
    }
}
