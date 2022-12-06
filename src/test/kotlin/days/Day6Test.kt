package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day6Test {

    private val day = Day6()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(7))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(19))
    }

    @Test
    fun findFirstMarker_SizeFour_CaseOne() {
        assertThat(day.findFirstMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 4), `is`(5))
    }

    @Test
    fun findFirstMarker_SizeFour_CaseTwo() {
        assertThat(day.findFirstMarker("nppdvjthqldpwncqszvftbrmjlhg", 4), `is`(6))
    }

    @Test
    fun findFirstMarker_SizeFour_CaseThree() {
        assertThat(day.findFirstMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4), `is`(10))
    }

    @Test
    fun findFirstMarker_SizeFour_CaseFour() {
        assertThat(day.findFirstMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4), `is`(11))
    }

    @Test
    fun findFirstMarker_SizeFour_CaseNoMarker() {
        assertThat(day.findFirstMarker("aaaaaaaaaaaaa", 4), `is`(-1))
    }

    @Test
    fun findFirstMarker_SizeFourteen_CaseOne() {
        assertThat(day.findFirstMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 14), `is`(23))
    }

    @Test
    fun findFirstMarker_SizeFourteen_CaseTwo() {
        assertThat(day.findFirstMarker("nppdvjthqldpwncqszvftbrmjlhg", 14), `is`(23))
    }

    @Test
    fun findFirstMarker_SizeFourteen_CaseThree() {
        assertThat(day.findFirstMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14), `is`(29))
    }

    @Test
    fun findFirstMarker_SizeFourteen_CaseFour() {
        assertThat(day.findFirstMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14), `is`(26))
    }

    @Test
    fun findFirstMarker_SizeFourteen_CaseNoMarker() {
        assertThat(day.findFirstMarker("aaaaaaaaaaaaa", 14), `is`(-1))
    }
}
