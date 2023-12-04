fun main() {

    fun part1(input: List<String>): Int {
        return input.mapIndexed { index, s ->
            val ss = s.drop("Card ${index + 1}".length)
            val (win, my) = ss.split(" | ")
            val winSet = win.split(" ").filter { it.isNotEmpty() }.toSet()
            val mySet = my.split(" ").filter { it.isNotEmpty() }.toSet()
            val size = winSet.intersect(mySet).size

            if (size == 0) {
                0
            } else {
                1 shl (size - 1)// pow(2, size - 1)
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.foldIndexed(IntArray(input.size) { 1 }) { index, copies, s ->
            val ss = s.drop("Card ${index + 1}".length)
            val (win, my) = ss.split(" | ")
            val winSet = win.split(" ").filter { it.isNotEmpty() }.toSet()
            val mySet = my.split(" ").filter { it.isNotEmpty() }.toSet()
            val size = winSet.intersect(mySet).size

            for (i in 1..size) {
                copies[index + i] += copies[index]
            }
            copies
        }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day04_test1")
    check(part1(testInput1) == 13)

    val testInput2 = readInput("Day04_test1")
    check(part2(testInput2) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
