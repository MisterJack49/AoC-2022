package days

class Day3 : Day(3) {

    override fun partOne(): Any {
        return inputList.map {
            it.take(it.length / 2) to it.takeLast(it.length / 2)
        }.map { groups -> groups.first[groups.first.indexOfAny(groups.second.toCharArray())] }
            .sumOf { Alphabet.valueOf(it.toString()).ordinal + 1 }

    }

    override fun partTwo(): Any {
        return inputList.chunked(3)
            .map { group ->
                val one = group[0].toList().distinct()
                val two = group[1].toList().distinct()
                val three = group[2].toList().distinct()

                one.first {two.contains(it) && three.contains(it) }
            }
            .sumOf { Alphabet.valueOf(it.toString()).ordinal + 1 }
    }

}

private enum class Alphabet {
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
}