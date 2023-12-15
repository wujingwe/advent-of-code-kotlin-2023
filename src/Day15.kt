fun main() {

    fun part1(input: List<String>): Int {
        return input[0].split(",").sumOf { hash(it) }
    }

    fun part2(input: List<String>): Int {
        val map = Array(256) {
            mutableListOf<Pair<String, Int>>()
        }
        input[0].split(",").forEach { s ->
            if (s.contains('=')) {
                val end = s.indexOf('=')
                val label = s.substring(0, end)
                val length = s.substring(end + 1).toInt()
                val num = hash(label)

                val found = map[num].withIndex().find { (_, pair) ->
                    pair.first == label
                }
                if (found != null) {
                    map[num][found.index] = label to length
                } else {
                    map[num].add(label to length)
                }
            } else { // '-'
                val end = s.indexOf('-')
                val label = s.substring(0, end)
                val num = hash(label)
                map[num].removeIf { (s, _) -> s == label }
            }
        }

        return map.withIndex().sumOf { (boxIndex, box) ->
            box.withIndex().sumOf {(slotIndex, lenses) ->
                (boxIndex + 1) * (slotIndex + 1) * lenses.second
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day15_test1")
    check(part1(testInput1) == 1320)

    val testInput2 = readInput("Day15_test1")
    check(part2(testInput2) == 145)

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}

fun hash(s: String): Int {
    return s.fold(0) { acc, c ->
        acc.let {
            (it + c.code) * 17 % 256
        }
    }
}