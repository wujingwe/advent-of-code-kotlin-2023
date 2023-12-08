fun main() {

    fun part1(input: List<String>): Int {
        val steps = input[0].toCharArray()

        val map = mutableMapOf<String, Pair<String, String>>()
        var i = 2
        while (i < input.size) {
            val (node, next) = input[i].split(" = ")
            val (left, right) = next.split(", ")
            map[node.trim()] = left.drop(1) to right.dropLast(1)
            ++i
        }

        var res = 0
        var pos = "AAA"
        var step = 0
        while (pos != "ZZZ") {
            pos = if (steps[step] == 'L') {
                map.getValue(pos).first
            } else {
                map.getValue(pos).second
            }
            step = (step + 1) % steps.size
            ++res
        }
        return res
    }

    fun part2(input: List<String>): Long {
        val steps = input[0].toCharArray()

        val map = mutableMapOf<String, Pair<String, String>>()
        val starts = mutableListOf<String>()
        var i = 2
        while (i < input.size) {
            val (node, next) = input[i].split(" = ")
            val (left, right) = next.split(", ")

            val start = node.trim()
            map[start] = left.drop(1) to right.dropLast(1)

            if (start.endsWith('A')) {
                starts.add(start)
            }

            ++i
        }

        val ans = starts.map { s ->
            var res = 0L
            var pos = s
            var step = 0
            while (!pos.endsWith('Z')) {
                pos = if (steps[step] == 'L') {
                    map.getValue(pos).first
                } else {
                    map.getValue(pos).second
                }
                step = (step + 1) % steps.size
                ++res
            }
            res
        }
        return ans.reduce { acc, n -> lcm(acc, n) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day08_test1")
    check(part1(testInput1) == 6)

    val testInput2 = readInput("Day08_test2")
    check(part2(testInput2) == 6L)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) {
        return a
    }
    return gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    val gcd = gcd(a, b)
    return a / gcd * b
}