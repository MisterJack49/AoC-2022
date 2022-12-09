package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day8Test {
    private val day = Day8()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(21))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(8))
    }

    @Test
    fun canSeeIncreasingToRequiredHeight() {
        val tree = Tree(height = 3)
        val trees = listOf( Tree(1), Tree(2), Tree(3))
        assertThat( tree.canSee(trees).count(), `is`(3))
    }

    @Test
    fun canSeeTwoOfSameSizeButBelowRequiredHeight() {
        val tree = Tree(height = 3)

        val trees = listOf( Tree(1), Tree(2), Tree(2))
        assertThat( tree.canSee(trees).count(), `is`(3))
    }

    @Test
    fun canSeeTwoOfSameSizeButAtRequiredHeight() {
        val tree = Tree(height = 3)

        val trees = listOf( Tree(1), Tree(3), Tree(3))
        assertThat( tree.canSee(trees).count(), `is`(2))
    }

    @Test
    fun canSeeSmallerBlockedByHigherTree() {
        val tree = Tree(height = 3)

        val trees = listOf( Tree(1), Tree(4), Tree(3))
        assertThat( tree.canSee(trees).count(), `is`(2))
    }
    
    @Test
    fun canSeeHigherBlockedByHigherTree() {
        val tree = Tree(height = 3)

        val trees = listOf( Tree(1), Tree(4), Tree(5))
        assertThat( tree.canSee(trees).count(), `is`(2))
    }
}
