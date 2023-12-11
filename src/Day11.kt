fun main() {

    fun part1(input: List<String>): Int {
        val rows = IntArray(input.size) { i ->
            if (input[i].any { it == '#' }) 1 else 2
        }
        val cols = IntArray(input[0].length) { j ->
            val array = CharArray(input.size) { input[it][j] }
            if (array.any { it == '#'}) 1 else 2
        }

        val accRows = rows.scan(0) { acc, r -> acc + r }
        val accCols = cols.scan(0) { acc, c -> acc + c }

        val galaxies = input.foldIndexed(mutableListOf<Pair<Int, Int>>()) { i, galaxies, s ->
            s.foldIndexed(galaxies) { j, acc, c ->
                if (c == '#') {
                    acc.add(i to j)
                }
                acc
            }
        }

        var sum = 0
        for (i in galaxies.indices) {
            for (j in i + 1 ..< galaxies.size) {
                val a = galaxies[i]
                val b = galaxies[j]
                val d = kotlin.math.abs(accRows[a.first] - accRows[b.first]) +
                        kotlin.math.abs(accCols[a.second] - accCols[b.second])
                sum += d
            }
        }
        return sum
    }

    fun part2(input: List<String>, expand: Int): Long {
        val rows = IntArray(input.size) { i ->
            if (input[i].any { it == '#' }) 1 else expand
        }
        val cols = IntArray(input[0].length) { j ->
            val array = CharArray(input.size) { input[it][j] }
            if (array.any { it == '#'}) 1 else expand
        }

        val accRows = rows.scan(0) { acc, r -> acc + r }
        val accCols = cols.scan(0) { acc, c -> acc + c }

        val galaxies = input.foldIndexed(mutableListOf<Pair<Int, Int>>()) { i, galaxies, s ->
            s.foldIndexed(galaxies) { j, acc, c ->
                if (c == '#') {
                    acc.add(i to j)
                }
                acc
            }
        }

        var sum = 0L
        for (i in galaxies.indices) {
            for (j in i + 1 ..< galaxies.size) {
                val a = galaxies[i]
                val b = galaxies[j]
                val d = kotlin.math.abs(accRows[a.first] - accRows[b.first]) +
                        kotlin.math.abs(accCols[a.second] - accCols[b.second])
                sum += d
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day11_test1")
    check(part1(testInput1) == 374)

    val testInput2 = readInput("Day11_test1")
    check(part2(testInput2, 10) == 1030L)
    check(part2(testInput2, 100) == 8410L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input, 1000000).println()
}
