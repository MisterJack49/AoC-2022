package days

import common.Coordinates
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day9Test {
    private val day = Day9()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(13))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(1))
    }

    @Test
    fun testPartTwoAlternate() {
        assertThat(Day9(true).partTwo(), `is`(36))
    }

    @Test
    fun isAdjacent_diag() {
        assertThat(Coordinates(0, 0).isAdjacent(Coordinates(1, 1)), `is`(true))
    }

    @Test
    fun isAdjacent_ortho() {
        assertThat(Coordinates(0, 1).isAdjacent(Coordinates(1, 1)), `is`(true))
    }

}
