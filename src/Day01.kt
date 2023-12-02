fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { s ->
            val first = s.firstOrNull { c -> c.isDigit() }?.digitToInt() ?: 0
            val second = s.lastOrNull { c -> c.isDigit() }?.digitToInt() ?: 0
            first * 10 + second
        }
    }

    val letters = listOf(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    )

    val digits = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

    fun part2(input: List<String>): Int {
        return input.sumOf { s ->
            val (firstLetterIndex, firstLetter) = s.findAnyOf(letters) ?: (Int.MAX_VALUE to "")
            val (firstDigitIndex, firstDigit) = s.findAnyOf(digits) ?: (Int.MAX_VALUE to "")
            val first = if (firstDigitIndex < firstLetterIndex) {
                firstDigit.toInt()
            } else {
                letters.indexOf(firstLetter) + 1
            }

            val (secondLetterIndex, secondLetter) = s.findLastAnyOf(letters) ?: (Int.MIN_VALUE to "")
            val (secondDigitIndex, secondDigit) = s.findLastAnyOf(digits) ?: (Int.MIN_VALUE to "")
            val second = if (secondDigitIndex > secondLetterIndex) {
                secondDigit.toInt()
            } else {
                letters.indexOf(secondLetter) + 1
            }

            first * 10 + second
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day01_test1")
    check(part1(testInput1) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
