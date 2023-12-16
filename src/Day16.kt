fun main() {

    val DIRS = mapOf(
        'r' to (0 to 1),
        'l' to (0 to -1),
        't' to (-1 to 0),
        'd' to (1 to 0)
    )

    fun calcEnergies(maze: Array<CharArray>, start: Triple<Char, Int, Int>): Int {
        val deque = ArrayDeque<Triple<Char, Int, Int>>()
        deque.add(start)

        val set = mutableSetOf<String>()
        while (deque.isNotEmpty()) {
            val size = deque.size

            repeat(size) {
                val (d, x, y) = deque.removeFirst()
                val (dx, dy) = DIRS.getValue(d)

                if (x >= 0 && x < maze.size && y >= 0 && y < maze[0].size) {
                    set.add("$d.$x.$y")

                    val candidates = when (maze[x][y]) {
                        '.' -> {
                            val nx = x + dx
                            val ny = y + dy
                            listOf(Triple(d, nx, ny))
                        }
                        '-' -> when (d) {
                            'r', 'l' -> {
                                val nx = x + dx
                                val ny = y + dy
                                listOf(Triple(d, nx, ny))
                            }
                            't', 'd' -> listOf(Triple('l', x, y - 1), Triple('r', x, y + 1))
                            else -> listOf()
                        }
                        '|' -> when (d) {
                            'r', 'l' -> listOf(Triple('t', x - 1, y), Triple('d', x + 1, y))
                            't', 'd' -> {
                                val nx = x + dx
                                val ny = y + dy
                                listOf(Triple(d, nx, ny))
                            }
                            else -> listOf()
                        }
                        '/' -> when (d) {
                            'r' -> listOf(Triple('t', x - 1, y))
                            'l' -> listOf(Triple('d', x + 1, y))
                            't' -> listOf(Triple('r', x, y + 1))
                            'd' -> listOf(Triple('l', x, y - 1))
                            else -> listOf()
                        }
                        '\\' -> when (d) {
                            'r' -> listOf(Triple('d', x + 1, y))
                            'l' -> listOf(Triple('t', x - 1, y))
                            't' -> listOf(Triple('l', x, y - 1))
                            'd' -> listOf(Triple('r', x, y + 1))
                            else -> listOf()
                        }
                        else -> listOf()
                    }
                    candidates.forEach { (d, nx, ny) ->
                        if (!set.contains("$d.$nx.$ny")) {
                            deque.add(Triple(d, nx, ny))
                        }
                    }
                }
            }
        }
        return set.map { it.substring(2) }.distinct().size
    }

    fun part1(input: List<String>): Int {
        val maze = Array(input.size) { i ->
            CharArray(input[i].length) { j ->
                input[i][j]
            }
        }
        return calcEnergies(maze, Triple('r', 0, 0))
    }

    fun part2(input: List<String>): Int {
        val maze = Array(input.size) { i ->
            CharArray(input[i].length) { j ->
                input[i][j]
            }
        }

        var max = 0
        for (i in input.indices) {
            max = maxOf(max, calcEnergies(maze, Triple('r', i, 0)))
        }
        for (i in input.indices) {
            max = maxOf(max, calcEnergies(maze, Triple('l', i, maze[0].size - 1)))
        }
        for (i in input[0].indices) {
            max = maxOf(max, calcEnergies(maze, Triple('t', maze.size - 1, i)))
        }
        for (i in input[0].indices) {
            max = maxOf(max, calcEnergies(maze, Triple('d', 0, i)))
        }
        return max
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day16_test1")
    check(part1(testInput1) == 46)

    val testInput2 = readInput("Day16_test1")
    check(part2(testInput2) == 51)

    val input = readInput("Day16")
    part1(input).println()
    part2(input).println()
}
