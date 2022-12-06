package days

class Day6 : Day(6) {
    override fun partOne(): Any {
        return findFirstMarker(inputString, 4)
    }

    override fun partTwo(): Any {
        return findFirstMarker(inputString, 14)
    }

    fun findFirstMarker(data: String, markerSize: Int): Int {
        for (index in data.indices) {
            if (index < markerSize - 1) continue
            if (data.slice(index - (markerSize - 1)..index).toList().distinct().count() == markerSize)
                return index + 1
        }
        return -1
    }

}
