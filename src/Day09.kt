fun main() {

    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, s ->
            val list = s.split(" ").map { it.toInt() }
            acc + findNext(list)
        }
    }

    fun part2(input: List<String>): Int {
        return input.fold(0) { acc, s ->
            val list = s.split(" ").map { it.toInt() }
            acc + findPrev(list)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day09_test1")
    check(part1(testInput1) == 114)

    val testInput2 = readInput("Day09_test1")
    check(part2(testInput2) == 2)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}

fun findNext(list: List<Int>): Int {
    if (list.all { it == 0 }) {
        return 0
    }
    val diffList = list.windowed(2) { it[1] - it[0] }
    return list.last() + findNext(diffList)
}

fun findPrev(list: List<Int>): Int {
    if (list.all { it == 0 }) {
        return 0
    }

    val diffList = list.windowed(2) { it[1] - it[0] }
    return list.first() - findPrev(diffList)
}