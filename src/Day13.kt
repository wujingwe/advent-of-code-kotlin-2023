fun main() {

    fun part1(input: List<String>): Long {
        var ans = 0L
        input.parts { list ->
            val maze = Array(list.size) { i ->
                CharArray(list[0].length) { j ->
                    list[i][j]
                }
            }

            val v = findV(maze) { diff -> diff == 0 }
            if (v != -1) {
                ans += v
            } else {
                val h = findH(maze) { diff -> diff == 0 }
                if (h != -1) {
                    ans += 100 * h
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        var ans = 0L
        input.parts { list ->
            val maze = Array(list.size) { i ->
                CharArray(list[0].length) { j ->
                    list[i][j]
                }
            }

            val v = findV(maze) { diff -> diff == 1 }
            if (v != -1) {
                ans += v
            } else {
                val h = findH(maze) { diff -> diff == 1 }
                if (h != -1) {
                    ans += 100 * h
                }
            }
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day13_test1")
    check(part1(testInput1) == 405L)

    val testInput2 = readInput("Day13_test1")
    check(part2(testInput2) == 400L)

    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}

fun findV(maze: Array<CharArray>, compare: (Int) -> Boolean): Int {
    val mazeV = Array(maze[0].size) { j ->
        CharArray(maze.size) { i ->
            maze[i][j]
        }
    }
    return findH(mazeV, compare)
}

fun findH(maze: Array<CharArray>, compare: (Int) -> Boolean): Int {
    var i = 0
    while (i < maze.size - 1) {
        var above = i
        var below = i + 1

        var diff = 0
        while (above >= 0 && below < maze.size) {
            diff += diff(maze[above], maze[below])
            if (diff > 1) {
                break
            }
            --above
            ++below
        }

        if (compare(diff) && (above == -1 || below == maze.size)) {
            return i + 1
        }

        ++i
    }
    return -1
}

fun diff(a: CharArray, b: CharArray): Int {
    var diff = 0
    for (i in a.indices) {
        if (a[i] != b[i]) {
            ++diff
            if (diff > 1) {
                break
            }
        }
    }
    return diff
}
