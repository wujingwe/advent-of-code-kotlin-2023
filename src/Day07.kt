import java.util.PriorityQueue

fun main() {

    fun part1(input: List<String>): Long {
        val pq = PriorityQueue<Triple<String, Int, Long>> { (a, ta), (b, tb) ->
            if (ta != tb) {
                ta - tb
            } else {
                compareChar(a, b)
            }
        }
        input.fold(pq) { q, s ->
            val (card, bit) = s.split(" ")
            q.apply {
                offer(Triple(card, getType(card), bit.toLong()))
            }
        }

        var ans = 0L
        var rank = 1
        while (pq.isNotEmpty()) {
            val el = pq.poll()
            ans += (el.third * rank)
            rank++
        }

        return ans
    }

    fun part2(input: List<String>): Long {
        val pq = PriorityQueue<Triple<String, Int, Long>> { (a, ta), (b, tb) ->
            if (ta != tb) {
                ta - tb
            } else {
                compareChar(a, b, true)
            }
        }
        input.fold(pq) { q, s ->
            val (card, bit) = s.split(" ")
            q.apply {
                offer(Triple(card, getType(card, true), bit.toLong()))
            }
        }

        var ans = 0L
        var rank = 1
        while (pq.isNotEmpty()) {
            val el = pq.poll()
            ans += (el.third * rank)
            rank++
        }

        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day07_test1")
    check(part1(testInput1) == 6440L)

    val testInput2 = readInput("Day07_test1")
    check(part2(testInput2) == 5905L)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

fun getType(s: String, joker: Boolean = false): Int {
    val map = mutableMapOf<Char, Int>()
    var jokerCount = 0
    s.forEach { c ->
        if (joker && c == 'J') {
            jokerCount++
        } else {
            map[c] = (map[c] ?: 0) + 1
        }
    }
    val list = map.values.toList().sorted().reversed()
    val max = if (joker) {
        if (list.isNotEmpty()) {
            list[0] + jokerCount
        } else {
            jokerCount
        }
    } else {
        list[0]
    }

    return when {
        max == 5 -> 7
        max == 4 -> 6
        max == 3 && list[1] == 2 -> 5
        max == 3 -> 4
        max == 2 && list[1] == 2 -> 3
        max == 2 -> 2
        else -> 1
    }
}

fun toCard(c: Char, joker: Boolean = false): Int {
    return when (c) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> if (joker) 1 else 11
        'T' -> 10
        '9' -> 9
        '8' -> 8
        '7' -> 7
        '6' -> 6
        '5' -> 5
        '4' -> 4
        '3' -> 3
        else -> 2
    }
}

fun compareChar(a: String, b: String, joker: Boolean = false): Int {
    for (i in a.indices) {
        if (a[i] != b[i]) {
            return toCard(a[i], joker) - toCard(b[i], joker)
        }
    }
    return 0
}
