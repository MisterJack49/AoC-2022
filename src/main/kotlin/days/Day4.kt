package days

class Day4 : Day(4) {

    private val reg = Regex("(\\d+)-(\\d+),(\\d+)-(\\d+)")
    override fun partOne(): Any {
        return inputList.mapNotNull { reg.find(it) }
            .map {
                Section(it.destructured.component1().toInt(), it.destructured.component2().toInt()) to
                        Section(it.destructured.component3().toInt(), it.destructured.component4().toInt())
            }.count { it.first.inbound(it.second) or it.second.inbound(it.first) }
    }

    override fun partTwo(): Any {
        return inputList.mapNotNull { reg.find(it) }
            .map {
                Section(it.destructured.component1().toInt(), it.destructured.component2().toInt()) to
                        Section(it.destructured.component3().toInt(), it.destructured.component4().toInt())
            }.count { it.first.overlap(it.second) or it.second.overlap(it.first) }
    }
}

private typealias Section = Pair<Int, Int>

private fun Section.inbound(other: Section) =
    this.first in other.first..other.second && this.second in other.first..other.second

private fun Section.overlap(other: Section) =
    this.first in other.first..other.second || this.second in other.first..other.second