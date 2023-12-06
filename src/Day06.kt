fun main() {

    fun part1(input: List<String>): Long {
        val times = input[0].drop("Time:".length)
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
        val distances = input[1].drop("Distance:".length)
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.toLong() }

        return times.foldIndexed(1L) { index, acc, t ->
            var begin = 0L // hold
            var end = t

            while (begin < end) {
                val mid = begin + (end - begin) / 2
                if ((t - mid) * mid > distances[index]) {
                    end = mid
                } else {
                    begin = mid + 1
                }
            }
            val remain = t - begin
            acc * (remain - begin + 1)
        }
    }

    fun part2(input: List<String>): Long {
        val time = input[0].drop("Time:".length)
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .reduce { acc, s -> acc + s }
            .toLong()
        val distance = input[1].drop("Distance:".length)
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .reduce { acc, s -> acc + s }
            .toLong()

        var begin = 0L // hold
        var end = time

        while (begin < end) {
            val mid = begin + (end - begin) / 2 // hold == speed
            if ((time - mid) * mid > distance) {
                end = mid
            } else {
                begin = mid + 1
            }
        }

        val remain = time - begin
        return remain - begin + 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day06_test1")
    check(part1(testInput1) == 288L)

    val testInput2 = readInput("Day06_test1")
    check(part2(testInput2) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
