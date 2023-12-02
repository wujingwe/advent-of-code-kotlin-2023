fun main() {
    val colors = listOf("red", "green", "blue")

    fun part1(input: List<String>): Int {
        return input.mapIndexed { index, s ->
            val cubes = s.drop("Game ${index + 1}: ".length).split(";", ",")
            val array = arrayOf(0, 0, 0)
            cubes.forEach { cube ->
                val (num, name) = cube.trim().split(" ")
                for (i in colors.indices) {
                    if (name == colors[i]) {
                        array[i] = maxOf(array[i], num.toInt())
                    }
                }
            }

            if (array[0] <= 12 && array[1] <= 13 && array[2] <= 14) {
                index + 1
            } else {
                0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.mapIndexed { index, s ->
            val cubes = s.drop("Game ${index + 1}: ".length).split(";", ",")
            val array = arrayOf(0, 0, 0)
            cubes.forEach { cube ->
                val (num, name) = cube.trim().split(" ")
                for (i in colors.indices) {
                    if (name == colors[i]) {
                        array[i] = maxOf(array[i], num.toInt())
                    }
                }
            }
            array.reduce { acc, num -> acc * num }
        }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day02_test1")
    check(part1(testInput1) == 8)

    val testInput2 = readInput("Day02_test1")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
