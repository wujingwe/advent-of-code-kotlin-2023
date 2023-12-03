val DIRS = listOf(
    -1 to -1,
    -1 to 0,
    -1 to 1,
    0 to -1,
    0 to 1,
    1 to -1,
    1 to 0,
    1 to 1
)

fun main() {

    fun part1(input: List<String>): Long {
        val array = input.to2DArray()
        val (m, n) = array.to2DSize()
        var sum = 0L

        for (row in 0..<m) {
            var j = 0
            while (j < n) {
                var c = array[row][j]
                if (c !in '0'..'9') {
                    j++
                    continue
                }

                val begin = j
                var current = 0L
                while (c in '0'..'9') {
                    current = current * 10L + c.digitToInt()
                    j++
                    if (j >= n) {
                        break
                    }
                    c = array[row][j]
                }

                range@ for (y in begin..j) {
                    for ((dx, dy) in DIRS) {
                        val nx = row + dx
                        val ny = y + dy
                        if (nx in 0..<m && ny in 0..<n) {
                            val neighbor = input[nx][ny]
                            if (neighbor != '.' && neighbor !in '0'..'9') {
                                sum += current
                                break@range
                            }
                        }
                    }
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val map = mutableMapOf<Pair<Int, Int>, MutableSet<Long>>()
        val array = input.to2DArray()
        val (m, n) = array.to2DSize()

        for (row in 0..<m) {
            var j = 0
            while (j < n) {
                var c = array[row][j]
                if (c !in '0'..'9') {
                    j++
                    continue
                }

                val begin = j
                var current = 0L
                while (c in '0'..'9') {
                    current = current * 10L + c.digitToInt()
                    j++
                    if (j >= n) {
                        break
                    }
                    c = array[row][j]
                }

                range@ for (y in begin..<j) {
                    for ((dx, dy) in DIRS) {
                        val nx = row + dx
                        val ny = y + dy
                        if (nx in 0..<m && ny in 0..<n) {
                            val neighbor = input[nx][ny]
                            if (neighbor == '*') {
                                map.computeIfAbsent(nx to ny) {
                                    mutableSetOf()
                                }.add(current)
                                break@range
                            }
                        }
                    }
                }
            }
        }

        return map.entries.map {
            it.value
        }.filter {
            it.size == 2
        }.sumOf {
            val (a, b) = it.toList()
            a * b
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day03_test1")
    check(part1(testInput1) == 4361L)

    val testInput2 = readInput("Day03_test1")
    check(part2(testInput2) == 467835L)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
